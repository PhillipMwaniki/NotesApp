package tech.philsoft.notesapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tech.philsoft.notesapp.R;
import tech.philsoft.notesapp.models.Note;

/**
 * Created by mwani on 1/2/2018.
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {

    private List<Note> mNotes;
    private Context mContext;

    public NoteListAdapter(List<Note> notes, Context context){
        mNotes = notes;
        mContext = context;

    }

    @Override
    public NoteListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note_list, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(NoteListAdapter.ViewHolder holder, int position) {

        holder.noteTitle.setText(mNotes.get(position).getTitle());
        holder.noteCreateDate.setText(mNotes.get(position).getReadableModifiedDate());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView noteTitle, noteCreateDate;



        public ViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.text_view_note_title);
            noteCreateDate = itemView.findViewById(R.id.text_view_note_date);
        }

    }
}
