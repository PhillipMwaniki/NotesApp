package tech.philsoft.notesapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import tech.philsoft.notesapp.R;
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
