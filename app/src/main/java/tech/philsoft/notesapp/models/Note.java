package tech.philsoft.notesapp.models;

import android.database.Cursor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import tech.philsoft.notesapp.utilities.Constants;

/**
 * Created by mwani on 1/1/2018.
 */

public class Note {
    private Long id;
    private String title;
    private String content;
    private Long dateCreated;
    private Long dateModified;

    public Note() {
    }

    public Note(Long id, String title, String content, Long dateCreated, Long dateModified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
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

    public Long getDateModified() {
        return dateModified;
    }

    public void setDateModified(Long dateModified) {
        this.dateModified = dateModified;
    }

    public String getReadableModifiedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy - h:mm a", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        Date modifiedDate = new Date(getDateModified());
        return sdf.format(modifiedDate);
    }

    public static Note getNotefromCursor(Cursor cursor){
        Note note = new Note();
        note.setId(cursor.getLong(cursor.getColumnIndex(Constants.COLUMN_ID)));
        note.setTitle(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_TITLE)));
        note.setContent(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_CONTENT)));
        note.setDateCreated(cursor.getLong(cursor.getColumnIndex(Constants.COLUMN_CREATED_TIME)));
        note.setDateModified(cursor.getLong(cursor.getColumnIndex(Constants.COLUMN_MODIFIED_TIME)));
        return note;
    }
}
