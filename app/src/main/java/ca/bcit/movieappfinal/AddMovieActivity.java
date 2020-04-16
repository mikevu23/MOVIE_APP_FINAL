package ca.bcit.movieappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AddMovieActivity extends AppCompatActivity {

    private static final String TAG = AddMovieActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        Intent mainActivity = getIntent();
    }

    public void onClickAddButton(View v) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference movieRef = db.collection("movies").document();

        EditText movieTitle = findViewById(R.id.movieTitle);
        EditText movieDescription = findViewById(R.id.movieDescription);
        EditText movieUrl = findViewById(R.id.URL);

        String title = movieTitle.getText().toString();
        String description = movieDescription.getText().toString();
        String url = movieUrl.getText().toString();

        Movie movie = new Movie(title, description, url);

        movieRef.set(movie)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddMovieActivity.this, "Movie saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddMovieActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });


        Intent addNewMovie = new Intent(AddMovieActivity.this, MainActivity.class);
        startActivity(addNewMovie);
    }
}

