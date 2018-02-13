package net.tvplay;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;

/**
 * Created by egork on 12.02.2018.
 */

public class MainFragment extends BrowseFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setupUI();
        loadRows();
    }

    public void setupUI(){
        setTitle("Test Title");
        setBrandColor(Color.GRAY);
    }

    private void loadRows(){
        ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        ItemPresenter itemPresenter = new ItemPresenter();
        ArrayObjectAdapter gridAdapter = new ArrayObjectAdapter(itemPresenter);
        gridAdapter.add(0,"item1");
        rowsAdapter.add(new ListRow(gridAdapter));
        setAdapter(rowsAdapter);
    }


}
