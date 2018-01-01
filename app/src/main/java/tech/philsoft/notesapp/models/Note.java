package tech.philsoft.notesapp.models;

/**
 * Created by mwani on 1/1/2018.
 */

public class Note {
    private Long id;
    private String title;
    private String content;
    private Long dateCreated;
    private Long dataModified;

    public Note() {
    }

    public Note(Long id, String title, String content, Long dateCreated, Long dataModified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.dataModified = dataModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getDataModified() {
        return dataModified;
    }

    public void setDataModified(Long dataModified) {
        this.dataModified = dataModified;
    }
}
