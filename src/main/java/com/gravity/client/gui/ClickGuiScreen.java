package com.gravity.client.gui;

import com.gravity.client.GravityClient;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

/**
 * Gravity ClickGUI — dark themed category panel GUI.
 * Press Right Shift to open.
 */
public class ClickGuiScreen extends Screen {

    // ─── Theme Colors ─────────────────────────────────────────
    private static final int BG_DEEP     = 0xFF080a0f;
    private static final int BG_BASE     = 0xFF0d1017;
    private static final int BG_SURFACE  = 0xFF131720;
    private static final int BG_HOVER    = 0xFF1a1f2e;
    private static final int BG_ACTIVE   = 0xFF1f2538;
    private static final int ACCENT      = 0xFF7c5cfc;
    private static final int ACCENT2     = 0xFF5b8af5;
    private static final int TEXT_1      = 0xFFf0f2f8;
    private static final int TEXT_2      = 0xFF8b91a8;
    private static final int TEXT_3      = 0xFF4a5068;
    private static final int SUCCESS     = 0xFF3dd68c;
    private static final int BORDER      = 0x18FFFFFF;

    // ─── Panel State ──────────────────────────────────────────
    private final List<Panel> panels = new ArrayList<>();
    private Panel draggingPanel = null;
    private int dragOffsetX, dragOffsetY;

    // Setting interaction
    private NumberSetting draggingSlider = null;
    private int sliderX, sliderWidth;
    
    // Keybind binding state
    private static Module bindingModule = null;

    public ClickGuiScreen() {
        super(Text.literal("Gravity Client"));
    }

    @Override
    protected void init() {
        if (panels.isEmpty()) {
            int startX = 20;
            int startY = 30;
            int panelWidth = 150;
            int gap = 4;

            for (Category category : Category.values()) {
                List<Module> modules = GravityClient.getInstance()
                        .getModuleManager().getModulesByCategory(category);
                panels.add(new Panel(category, modules, startX, startY, panelWidth));
                startX += panelWidth + gap;
            }
        } else {
            for (Panel panel : panels) {
                panel.modules.clear();
                panel.modules.addAll(GravityClient.getInstance()
                        .getModuleManager().getModulesByCategory(panel.category));
            }
        }
        bindingModule = null;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Semi-transparent background
        context.fill(0, 0, width, height, 0xAA000000);

        // Title
        String title = "⚡ Gravity Client v1.0";
        context.drawText(textRenderer, title, 6, 4, ACCENT, true);
        String hint = "Right Shift to close";
        context.drawText(textRenderer, hint, width - textRenderer.getWidth(hint) - 6, 4, TEXT_3, false);

        // Render panels
        for (Panel panel : panels) {
            panel.render(context, mouseX, mouseY, textRenderer);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int mx = (int) mouseX, my = (int) mouseY;

        for (Panel panel : panels) {
            // Check panel header drag
            if (mx >= panel.x && mx <= panel.x + panel.width && my >= panel.y && my <= panel.y + 20) {
                if (button == 1) {
                    // Right click to collapse
                    panel.collapsed = !panel.collapsed;
                    return true;
                }
                draggingPanel = panel;
                dragOffsetX = mx - panel.x;
                dragOffsetY = my - panel.y;
                return true;
            }

            if (panel.collapsed) continue;

            int y = panel.y + 22;
            for (Module module : panel.modules) {
                int rowHeight = 16;

                // Check keybind badge click first!
                String kb = module.getKeyBindName();
                int kbWidth = textRenderer.getWidth(kb);
                int kbX = panel.x + panel.width - kbWidth - 10;
                int kbY = y + 2;

                if (mx >= kbX && mx <= panel.x + panel.width - 4 && my >= kbY && my <= y + 13) {
                    if (button == 0) {
                        bindingModule = module;
                        return true;
                    }
                }

                // Module row click
                if (mx >= panel.x && mx <= panel.x + panel.width && my >= y && my <= y + rowHeight) {
                    if (button == 0) {
                        module.toggle();
                    } else if (button == 1) {
                        panel.toggleExpanded(module);
                    }
                    return true;
                }
                y += rowHeight;

                // Settings area
                if (panel.isExpanded(module)) {
                    for (Setting<?> setting : module.getSettings()) {
                        if (setting instanceof BooleanSetting bs) {
                            if (mx >= panel.x + 4 && mx <= panel.x + panel.width - 4 && my >= y && my <= y + 14) {
                                bs.toggle();
                                GravityClient.getInstance().getConfigManager().save();
                                return true;
                            }
                            y += 14;
                        } else if (setting instanceof NumberSetting ns) {
                            int slX = panel.x + 6;
                            int slW = panel.width - 12;
                            if (mx >= slX && mx <= slX + slW && my >= y + 10 && my <= y + 22) {
                                draggingSlider = ns;
                                sliderX = slX;
                                sliderWidth = slW;
                                updateSliderValue(mx);
                                return true;
                            }
                            y += 24;
                        } else if (setting instanceof ModeSetting ms) {
                            if (mx >= panel.x + 4 && mx <= panel.x + panel.width - 4 && my >= y && my <= y + 14) {
                                ms.cycle();
                                GravityClient.getInstance().getConfigManager().save();
                                return true;
                            }
                            y += 14;
                        }
                    }
                    y += 2; // padding after settings
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        draggingPanel = null;
        if (draggingSlider != null) {
            draggingSlider = null;
            GravityClient.getInstance().getConfigManager().save();
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        int mx = (int) mouseX, my = (int) mouseY;

        if (draggingPanel != null) {
            draggingPanel.x = mx - dragOffsetX;
            draggingPanel.y = my - dragOffsetY;
            return true;
        }

        if (draggingSlider != null) {
            updateSliderValue(mx);
            return true;
        }

        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    private void updateSliderValue(int mouseX) {
        if (draggingSlider == null) return;
        double ratio = Math.max(0, Math.min(1, (double)(mouseX - sliderX) / sliderWidth));
        double value = draggingSlider.getMin() + ratio * (draggingSlider.getMax() - draggingSlider.getMin());
        draggingSlider.setValue(value);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (bindingModule != null) {
            if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
                bindingModule = null;
            } else if (keyCode == GLFW.GLFW_KEY_DELETE || keyCode == GLFW.GLFW_KEY_BACKSPACE) {
                bindingModule.setKeyBind(-1);
                GravityClient.getInstance().getConfigManager().save();
                bindingModule = null;
            } else {
                bindingModule.setKeyBind(keyCode);
                GravityClient.getInstance().getConfigManager().save();
                bindingModule = null;
            }
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    // ═══════════════════════════════════════════════════════════
    // PANEL — represents a category column
    // ═══════════════════════════════════════════════════════════
    private static class Panel {
        final Category category;
        final List<Module> modules;
        int x, y, width;
        boolean collapsed = false;
        private Module expandedModule = null;

        Panel(Category category, List<Module> modules, int x, int y, int width) {
            this.category = category;
            this.modules = modules;
            this.x = x;
            this.y = y;
            this.width = width;
        }

        boolean isExpanded(Module m) { return expandedModule == m; }
        void toggleExpanded(Module m) { expandedModule = (expandedModule == m) ? null : m; }

        void render(DrawContext ctx, int mx, int my, net.minecraft.client.font.TextRenderer font) {
            // ─── Panel Header ─────────────────────────────────
            ctx.fill(x, y, x + width, y + 20, ACCENT);
            ctx.drawText(font, category.getName() + " (" + modules.size() + ")",
                    x + 6, y + 6, 0xFFFFFFFF, true);
            // Collapse indicator
            ctx.drawText(font, collapsed ? "►" : "▼", x + width - 12, y + 6, 0xCCFFFFFF, false);

            if (collapsed) return;

            // ─── Module Rows ──────────────────────────────────
            int curY = y + 22;

            for (Module module : modules) {
                int rowHeight = 16;
                boolean hovered = mx >= x && mx <= x + width && my >= curY && my <= curY + rowHeight;

                // Row background
                int rowBg = module.isEnabled() ? BG_ACTIVE : (hovered ? BG_HOVER : BG_SURFACE);
                ctx.fill(x, curY, x + width, curY + rowHeight, rowBg);

                // Module name
                int nameColor = module.isEnabled() ? SUCCESS : (hovered ? TEXT_1 : TEXT_2);
                ctx.drawText(font, module.getName(), x + 6, curY + 4, nameColor, false);

                // Keybind badge
                String kb = bindingModule == module ? "?" : module.getKeyBindName();
                int kbWidth = font.getWidth(kb);
                int kbX = x + width - kbWidth - 10;
                int kbY = curY + 2;
                ctx.fill(kbX, kbY, x + width - 4, curY + 13, BG_DEEP);
                ctx.drawText(font, kb, kbX + 3, curY + 4, bindingModule == module ? ACCENT : (module.getKeyBind() == -1 ? TEXT_3 : ACCENT2), false);

                // Enabled indicator dot
                if (module.isEnabled()) {
                    ctx.fill(x + 1, curY, x + 3, curY + rowHeight, SUCCESS);
                }

                // Settings arrow if has settings
                if (!module.getSettings().isEmpty()) {
                    ctx.drawText(font, isExpanded(module) ? "▾" : "›",
                            x + width - font.getWidth(bindingModule == module ? "?" : module.getKeyBindName()) - 16,
                            curY + 4, TEXT_3, false);
                }

                curY += rowHeight;

                // ─── Expanded Settings ────────────────────────
                if (isExpanded(module)) {
                    ctx.fill(x, curY - 1, x + width, curY, BORDER);

                    for (Setting<?> setting : module.getSettings()) {
                        if (setting instanceof BooleanSetting bs) {
                            ctx.fill(x + 2, curY, x + width - 2, curY + 14, BG_DEEP);
                            ctx.drawText(font, setting.getName(), x + 8, curY + 3, TEXT_2, false);
                            // Toggle indicator
                            int toggleX = x + width - 16;
                            ctx.fill(toggleX, curY + 3, toggleX + 10, curY + 11, bs.isEnabled() ? SUCCESS : TEXT_3);
                            curY += 14;
                        } else if (setting instanceof NumberSetting ns) {
                            ctx.fill(x + 2, curY, x + width - 2, curY + 24, BG_DEEP);
                            String label = ns.getName() + ": " + String.format("%.1f", ns.getValue());
                            ctx.drawText(font, label, x + 8, curY + 2, TEXT_2, false);
                            // Slider track
                            int slX = x + 6, slY = curY + 13, slW = width - 12;
                            ctx.fill(slX, slY, slX + slW, slY + 4, BG_ACTIVE);
                            // Filled portion
                            double maxMinDiff = ns.getMax() - ns.getMin();
                            double ratio = maxMinDiff == 0 ? 0 : (ns.getValue() - ns.getMin()) / maxMinDiff;
                            ctx.fill(slX, slY, slX + (int)(slW * ratio), slY + 4, ACCENT);
                            // Thumb
                            int thumbX = slX + (int)(slW * ratio) - 2;
                            ctx.fill(thumbX, slY - 2, thumbX + 5, slY + 6, 0xFFFFFFFF);
                            curY += 24;
                        } else if (setting instanceof ModeSetting ms) {
                            ctx.fill(x + 2, curY, x + width - 2, curY + 14, BG_DEEP);
                            ctx.drawText(font, ms.getName() + ": " + ms.getValue(), x + 8, curY + 3, ACCENT2, false);
                            curY += 14;
                        } else if (setting instanceof ColorSetting cs) {
                            ctx.fill(x + 2, curY, x + width - 2, curY + 14, BG_DEEP);
                            ctx.drawText(font, cs.getName(), x + 8, curY + 3, TEXT_2, false);
                            ctx.fill(x + width - 18, curY + 3, x + width - 6, curY + 11, cs.getValue() | 0xFF000000);
                            curY += 14;
                        }
                    }
                    curY += 2;
                }

                // Bottom border between modules
                ctx.fill(x + 2, curY - 1, x + width - 2, curY, BORDER);
            }

            // Panel bottom
            ctx.fill(x, curY, x + width, curY + 2, BG_ACTIVE);
        }
    }
}
