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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.widget.Toast;

import java.util.Date;

public class FirebaseConexion {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase myDb;
    private DatabaseReference myRef;
    private String userID;

    private static final String TAG = "FirebaseConexion";

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

    public void crearProfesor(Profesores profesor){
        myRef.child("Profesores")
                .child(profesor.getCedula())
                .setValue(profesor);
    }


    public void asignarUsuario(Profesores profesor,String id){

        myRef.child("Cursos").child(id).setValue(profesor);

    }


    public Curso consultarCurso(String id){

        final String idCompare = id;

        final String[] valores = new String[8];

        myDb.getReference("Cursos").getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String idActual = postSnapshot.child("id").getValue().toString();

                    if(idActual.compareTo(idCompare) == 0){
                        valores[0] = postSnapshot.child("id").getValue().toString();
                        Log.e(TAG, "======="+postSnapshot.child("id").getValue());

                        valores[1] = postSnapshot.child("nombre").getValue().toString();
                        Log.e(TAG, "======="+postSnapshot.child("nombre").getValue());

                        valores[2] = postSnapshot.child("descripcion").getValue().toString();
                        Log.e(TAG, "======="+postSnapshot.child("descripcion").getValue());

                        valores[3] = postSnapshot.child("fechaInicio").getValue().toString();
                        Log.e(TAG, "======="+postSnapshot.child("fechaInicio").getValue());

                        valores[4] = postSnapshot.child("fechaFinal").getValue().toString();
                        Log.e(TAG, "======="+postSnapshot.child("fechaFinal").getValue());

                        valores[5] = postSnapshot.child("horario").getValue().toString();
                        Log.e(TAG, "======="+postSnapshot.child("horario").getValue());

                        valores[6] = postSnapshot.child("fechaParciales").getValue().toString();
                        Log.e(TAG, "======="+postSnapshot.child("fechaParciales").getValue());

                        valores[7] = postSnapshot.child("novedades").getValue().toString();
                        Log.e(TAG, "======="+postSnapshot.child("novedades").getValue());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });

        for(int i = 0; i < 8; i++){
            Log.e(TAG, "======="+valores[i]);
        }

        return new Curso(valores[0],valores[1],valores[2],valores[3],valores[4],valores[5],valores[6],valores[7]);

    }

}
