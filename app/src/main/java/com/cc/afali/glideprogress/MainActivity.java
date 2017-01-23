package com.cc.afali.glideprogress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cc.afali.glideprogress.adapter.ProgressAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewImages)
    RecyclerView recyclerView;

    ArrayList<String> pictureURLCollection;
    ProgressAdapter progressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupSampleCollection();
        setupRecycleView();
    }

    private void setupRecycleView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        progressAdapter = new ProgressAdapter(pictureURLCollection);
        recyclerView.setAdapter(progressAdapter);
    }

    // few results from https://www.google.com/search?tbm=isch&q=image&tbs=isz:lt,islt:4mp
    private void setupSampleCollection() {
        pictureURLCollection = new ArrayList<>();

        pictureURLCollection.add("http://www.noaanews.noaa.gov/stories/images/goes-12%2Dfirstimage-large081701%2Ejpg");
        pictureURLCollection.add("http://www.spektyr.com/PrintImages/Cerulean%20Cross%203%20Large.jpg");
        pictureURLCollection.add("https://cdn.photographylife.com/wp-content/uploads/2014/06/Nikon-D810-Image-Sample-6.jpg");
        pictureURLCollection.add("https://upload.wikimedia.org/wikipedia/commons/5/5b/Ultraviolet_image_of_the_Cygnus_Loop_Nebula_crop.jpg");
        pictureURLCollection.add("https://upload.wikimedia.org/wikipedia/commons/c/c5/Polarlicht_2_kmeans_16_large.png");
        pictureURLCollection.add("https://www.hq.nasa.gov/alsj/a15/M1123519889LCRC_isometric_min-8000_g0dot5_enhanced_labeled.jpg");
        pictureURLCollection.add("http://oceanexplorer.noaa.gov/explorations/02fire/logs/hirez/octopus_hires.jpg");
        pictureURLCollection.add("https://upload.wikimedia.org/wikipedia/commons/b/bf/GOES-13_First_Image_jun_22_2006_1730Z.jpg");
        pictureURLCollection.add("http://www.zastavki.com/pictures/originals/2013/Photoshop_Image_of_the_horse_053857_.jpg");
        pictureURLCollection.add("http://www.marcogiordanotd.com/blog/wp-content/uploads/2014/01/image9Kcomp.jpg");
        pictureURLCollection.add("https://cdn.photographylife.com/wp-content/uploads/2014/06/Nikon-D810-Image-Sample-7.jpg");
        pictureURLCollection.add("https://www.apple.com/v/imac-with-retina/a/images/overview/5k_image.jpg");
        pictureURLCollection.add("https://www.gimp.org/tutorials/Lite_Quickies/lordofrings_hst_big.jpg");
        pictureURLCollection.add("http://www.cesbio.ups-tlse.fr/multitemp/wp-content/uploads/2015/07/Mad%C3%A8re-022_0_1.jpg");
        pictureURLCollection.add("https://www.spacetelescope.org/static/archives/fitsimages/large/slawomir_lipinski_04.jpg");
        pictureURLCollection.add("https://upload.wikimedia.org/wikipedia/commons/b/b4/Mardin_1350660_1350692_33_images.jpg");
        pictureURLCollection.add("http://4k.com/wp-content/uploads/2014/06/4k-image-tiger-jumping.jpg");
    }
}
