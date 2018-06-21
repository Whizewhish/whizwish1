package com.alpha.whizwish.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.whizwish.Activities.AddNew;
import com.alpha.whizwish.Activities.GenieActivity;
import com.alpha.whizwish.Activities.ProfileActivity;
import com.alpha.whizwish.Adapters.SelectUsersAdapter;
import com.alpha.whizwish.Models.SelectUsersModel;
import com.alpha.whizwish.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ShareMemory extends Fragment {

    private static final int PICK_IMAGE = 100 ;
    private static final int PLACE_PICKER_REQUEST = 1;
    private Uri image_uri;
    private ImageView caption_image;
    private LinearLayout add_caption,add_location, tag_friends;
    private List<SelectUsersModel> modelListSheet;
    private RecyclerView usersRecycler;
    private TextView sheetHeader;
    private Button done , cancel, post, cancelPost;


    public ShareMemory() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_share_memory, container, false);
        final View sheet = getLayoutInflater().inflate(R.layout.tag_friends_bottom_sheet, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
        dialog.setContentView(sheet);
        sheetHeader = (TextView) sheet.findViewById(R.id.sheetHeader);
        done = (Button) sheet.findViewById(R.id.done);
        cancel = (Button) sheet.findViewById(R.id.cancel);
        sheetHeader.setText("Tag Friends");

        post=(Button) view.findViewById(R.id.post);
        cancelPost= (Button) view.findViewById(R.id.cancel);

        AddNew imageUriActivity = (AddNew) getActivity();
        String path = imageUriActivity.getImageData();



        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "tag done!", Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "user canceled !", Toast.LENGTH_SHORT).show();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Posted Successfully!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), ProfileActivity.class);
                startActivity(i);
            }
        });
        cancelPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "User Canceled !", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), GenieActivity.class);
                startActivity(i);
            }
        });
        usersRecycler = (RecyclerView) sheet.findViewById(R.id.usersRecycler);
        usersRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        usersRecycler.setHasFixedSize(true);
        add_caption = (LinearLayout) view.findViewById(R.id.add_caption);
        caption_image = (ImageView) view.findViewById(R.id.caption_image);
        add_location = (LinearLayout) view.findViewById(R.id.add_location);
        tag_friends = (LinearLayout) view.findViewById(R.id.tag_friends);


        Glide.with(getActivity()).load(path).into(caption_image);

      /*  add_caption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });*/

        add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLocation();
            }
        });

        new loadUsersSheet().execute();
        tag_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
            }
        });
        return view;
    }

    private void selectLocation() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            Intent i  = builder.build(getActivity());
            startActivityForResult(i, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }


   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK && data != null) {
                Place place = PlacePicker.getPlace(data, getActivity());
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(getActivity(), toastMsg, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                image_uri = data.getData();
                caption_image.setImageURI(image_uri);
            }
        }
    }*/

    private class loadUsersSheet extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            modelListSheet = new ArrayList<>();
            modelListSheet.add(new SelectUsersModel(R.drawable.profile1));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile2));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile3));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile4));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile5));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile4));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile1));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile7));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile9));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile10));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile11));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile1));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile3));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile9));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile3));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile8));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile1));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile5));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile2));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile7));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile5));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile1));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile9));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile1));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile2));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile3));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile4));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile5));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile4));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile1));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile7));
            modelListSheet.add(new SelectUsersModel(R.drawable.profile9));


            SelectUsersAdapter selectUsersAdapter = new SelectUsersAdapter(modelListSheet, getContext());
            usersRecycler.setAdapter(selectUsersAdapter);
            selectUsersAdapter.notifyDataSetChanged();

            return null;
        }
    }



}