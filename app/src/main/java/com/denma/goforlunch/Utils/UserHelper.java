package com.denma.goforlunch.Utils;

import com.google.android.gms.tasks.Task;
import com.denma.goforlunch.Models.Firebase.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserHelper {

    private static final String COLLECTION_NAME = "users";

    // --- COLLECTION REFERENCE ---
    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---
    public static Task<Void> createUser(String uid, String username, String mail, String urlPicture, String lunchRestaurant){
        User userToCreate = new User(uid, username, mail, urlPicture, lunchRestaurant);
        return UserHelper.getUsersCollection().document(uid).set(userToCreate);
    }

    // --- READ ---
    public static Task<DocumentSnapshot> getUser(String uid){
        return UserHelper.getUsersCollection().document(uid).get();
    }

    // --- UPDATE ---
    public static Task<Void> updateUsername(String username, String uid){
        return UserHelper.getUsersCollection().document(uid).update("username", username);
    }

    public static Task<Void> updateMail(String uid, String mail){
        return UserHelper.getUsersCollection().document(uid).update("mail", mail);
    }

    public static Task<Void> updateLunch(String uid, String lunchRestaurant){
        return UserHelper.getUsersCollection().document(uid).update("lunchRestaurant", lunchRestaurant);
    }

    public static Task<Void> addLike(String uid, String restId){
        Map<String, Object> mock = new HashMap<>();
        return UserHelper.getUsersCollection().document(uid).collection("restLike").document(restId).set(mock);
    }

    // --- DELETE ---
    public static Task<Void> deleteUser(String uid){
        return UserHelper.getUsersCollection().document(uid).delete();
    }

    public static Task<Void> deleteLike(String uid, String restId){
        return UserHelper.getUsersCollection().document(uid).collection("restLike").document(restId).delete();
    }
}
