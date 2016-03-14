package com.giologic.pedrosystem10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.giologic.pedrosystem10.model.MySQLSSHConnector;
import com.giologic.pedrosystem10.model.Post;
import com.giologic.pedrosystem10.service.PostService;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeHeadersAdapter adapter;

    private OnFragmentInteractionListener mListener;

    private String dbUrl = "jdbc:mysql://localhost:5656/mobapde";
    private String username = "pedromax";
    private String password = "marcsanpedro";

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_home);
        adapter = new HomeHeadersAdapter();

        new InitSSH().execute();
        new PostSSH().execute();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class InitSSH extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            MySQLSSHConnector.getInstance().getConnection();
            return null;
        }
    }

    /*class OrgSSH extends AsyncTask<Void, Void, Collection<Organization>> {

        @Override
        protected Collection<Organization> doInBackground(Void... params) {
            try {
                OrgService oService = new OrgService(dbUrl, username, password);
                return oService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Collection<Organization> orgs) {
            if(orgs.size()==0){
                tv.setText("Awww, no students retrieved");
            } else {
                String out = "";
                for(Organization cur: orgs){
                    out+=cur.getName()+"\n";
                    Collection<Post> posts = cur.getPosts();
                    for(Post p: posts){
                        byte[] data = p.getImg();
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        iv.setImageBitmap(bmp);
                    }
                    if(posts==null){
                        byte[] data = cur.getLogo();
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        iv.setImageBitmap(bmp);
                    }
                }
                tv.setText(out);
            }
        }
    }

    class AsyncSSH extends AsyncTask<Void, Void, Collection<AcademicAnnouncement>> {

        @Override
        protected Collection<AcademicAnnouncement> doInBackground(Void... params) {
            try {
                AAService aaService = new AAService(dbUrl, username, password);
                return aaService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Collection<AcademicAnnouncement> strings) {
            if(strings.size()==0){
                tv.setText("Awww, no students retrieved");
            } else {
                String out = "";
                for(AcademicAnnouncement cur: strings){
                    out+=cur.getTitle()+"\n";
                    byte[] data = cur.getImg();
                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    iv.setImageBitmap(bmp);
                }
                tv.setText(out);
            }
        }
    }*/

    class PostSSH extends AsyncTask<Void, Void, Collection<Post>> {

        @Override
        protected Collection<Post> doInBackground(Void... params) {
            try{
                PostService pService = new PostService(dbUrl, username, password);
                return pService.findAll();
            } catch (Exception e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Collection<Post> posts) {
            /*if(posts.size()==0){
                tv.setText("Awww, no students retrieved");
            } else {
                String out = "";
                for(Post cur: posts){
                    out+=cur.getTitle()+"\n";
                    Organization org = cur.getOrg();
                    if(org==null){
                        byte[] data = cur.getImg();
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        iv.setImageBitmap(bmp);
                    } else {
                        byte[] data = org.getLogo();
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        iv.setImageBitmap(bmp);
                    }
                }
                tv.setText(out);
            }*/
            for(Post p : posts) {
                adapter.add(p);
            }
        }
    }
}
