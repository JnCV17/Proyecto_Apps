package Mundo;


import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Toast;

import java.util.Date;

public class FirebaseConexion {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase myDb;
    private DatabaseReference myRef;
    private String userID;

    private static final String TAG = "FirebaseMethods";

    //vars
    private Context mContext;

    public FirebaseConexion(Context context) {
        mAuth = FirebaseAuth.getInstance();
        myDb = FirebaseDatabase.getInstance();
        myRef = myDb.getReference();
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

    public void crearCurso(Curso curso){

         myRef.child("Cursos")
                .child(curso.getId())
                .setValue(curso);
    }


    public Curso consultarCurso(String id){

        final String id1 = id;
        final Curso[] c1 = new Curso[1];
        DatabaseReference cursosRef = myDb.getReference(id);
        cursosRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Curso c = dataSnapshot.getValue(Curso.class);
                    c1[0] = new Curso(c.getId(),c.getNombre(),c.getDescripcion(),c.getFechaInicio(),c.getFechaFinal(),c.getHorario(),c.getFechaParciales(),c.getNovedades());
                    System.out.println(dataSnapshot.getKey() + " CURSO: " + c1[0].getId() + " meters tall.");


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            // ...
        });
        return c1[0];
    }






}
