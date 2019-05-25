package Mundo;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Toast;

public class FirebaseConexion {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String userID;

    private static final String TAG = "FirebaseMethods";

    //vars
    private Context mContext;

    public FirebaseConexion(Context context) {
        mAuth = FirebaseAuth.getInstance();

        mContext = context;


    }

    public void onStart() {
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            userID = currentUser.getUid();
        }
    }

    public void createAccount(final String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                        else if(task.isSuccessful()){

                            Toast.makeText(mContext, "Authentication Succeful.",
                                    Toast.LENGTH_SHORT).show();

                            userID = mAuth.getCurrentUser().getUid();

                            Log.d(TAG, "onComplete: Authstate changed: " + userID);


                        }

                    }
                });
    }


    private void escribirBD(){



    }



}
