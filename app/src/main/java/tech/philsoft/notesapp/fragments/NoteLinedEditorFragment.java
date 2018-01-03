package tech.philsoft.notesapp.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import tech.philsoft.notesapp.R;
import tech.philsoft.notesapp.activities.MainActivity;
import tech.philsoft.notesapp.data.NoteManager;
import tech.philsoft.notesapp.models.Note;

public class NoteLinedEditorFragment extends Fragment {

    private View mRootView;
    private EditText mTitleEditText;
    private EditText mContentEditText;
    private Note mCurrentNote = null;

    private OnFragmentInteractionListener mListener;

    public NoteLinedEditorFragment() {
        // Required empty public constructor
    }

    public static NoteLinedEditorFragment newInstance(long id) {
        NoteLinedEditorFragment fragment = new NoteLinedEditorFragment();

        if (id > 0){
            Bundle bundle = new Bundle();
            bundle.putLong("id", id);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    private void getCurrentNote(){
        Bundle args = getArguments();
        if (args != null && args.containsKey("id")){
            long id = args.getLong("id", 0);
            if (id > 0){
                mCurrentNote = NoteManager.newInstance(getActivity()).getNote(id);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getCurrentNote();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCurrentNote != null){
            populateFields();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_note_edit_plain, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                if (mCurrentNote != null){
                promptForDelete();
            }else {
                makeToast("Cannot delete note that has not been saved");
            }
                break;
            case R.id.action_save:
                if (saveNote()){
                    makeToast(mCurrentNote !=  null ? "Note updated" : "Note saved");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void promptForDelete(){
        final String titleOfNoteTobeDeleted = mCurrentNote.getTitle();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Delete " + titleOfNoteTobeDeleted + " ?");
        alertDialog.setMessage("Are you sure you want to delete the note " + titleOfNoteTobeDeleted + "?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NoteManager.newInstance(getActivity()).delete(mCurrentNote);
                makeToast(titleOfNoteTobeDeleted + "deleted");
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private boolean saveNote(){

        String title = mTitleEditText.getText().toString();
        if (TextUtils.isEmpty(title)){
            mTitleEditText.setError("Title is required");
            return false;
        }

        String content = mContentEditText.getText().toString();
        if (TextUtils.isEmpty(content)){
            mContentEditText.setError("Content is required");
            return false;
        }


        if (mCurrentNote != null){
            mCurrentNote.setContent(content);
            mCurrentNote.setTitle(title);
            NoteManager.newInstance(getActivity()).update(mCurrentNote);

        }else {
            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);
            NoteManager.newInstance(getActivity()).create(note);
        }
        return true;

    }

    private void populateFields() {
        mTitleEditText.setText(mCurrentNote.getTitle());
        mContentEditText.setText(mCurrentNote.getContent());
    }

    private void makeToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_note_plain_editor, container, false);
        mTitleEditText = mRootView.findViewById(R.id.edit_text_title);
        mContentEditText = mRootView.findViewById(R.id.edit_text_note);
        return mRootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
