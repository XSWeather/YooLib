package dev.yooproject.yoolib.entity;

public class YooNPC extends YooEntity {
    protected String skin;
    protected boolean aiEnabled = true;
    protected String dialog;

    public YooNPC() { this.type = YooEntityType.NPC; }

    public YooNPC setSkin(String skin) { this.skin = skin; return this; }
    public YooNPC setAIEnabled(boolean enabled) { this.aiEnabled = enabled; return this; }
    public YooNPC setDialog(String dialog) { this.dialog = dialog; return this; }
}
