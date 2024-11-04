package org.example.model;

public class PapelPagePermissao {
    int id;
    int papelId;
    int pageId;

    public PapelPagePermissao(int id, int papelId, int pageId) {
        this.id = id;
        this.papelId = papelId;
        this.pageId = pageId;
    }
    public PapelPagePermissao() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPapelId() {
        return papelId;
    }
    public void setPapelId(int papelId) {
        this.papelId = papelId;
    }
    public int getPageId() {
        return pageId;
    }
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
