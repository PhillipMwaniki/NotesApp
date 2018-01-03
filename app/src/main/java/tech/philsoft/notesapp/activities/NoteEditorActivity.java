package tech.philsoft.notesapp.activities;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import tech.philsoft.notesapp.R;
import tech.philsoft.notesapp.fragments.NoteLinedEditorFragment;
import tech.philsoft.notesapp.fragments.NotePlainEditorFragment;

public class NoteEditorActivity extends AppCompatActivity implements NoteLinedEditorFragment.OnFragmentInteractionListener {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null){
            Bundle args = getIntent().getExtras();
            if (args != null && args.containsKey("id")){
                long id = args.getLong("id", 0);
                if (id > 0){
                    NoteLinedEditorFragment fragment = NoteLinedEditorFragment.newInstance(id);
                    openFragment(fragment);
                }
            }
            openFragment(new NoteLinedEditorFragment());
        }
    }

    private void openFragment(final Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle("Editor");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
