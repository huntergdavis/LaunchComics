package com.hunterdavis.comics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hunterdavis.comics.content.ComicContent;

import java.util.Calendar;
import java.util.Date;


/**
 * An activity representing a list of Comics. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ComicDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ComicListFragment} and the item details
 * (if present) is a {@link ComicDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ComicListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ComicListActivity extends FragmentActivity
        implements ComicListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_list);

        if (findViewById(R.id.comic_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ComicListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.comic_list))
                    .setActivateOnItemClick(true);
        }

        WebView[] webViews = new WebView[18];

        WebViewClient ourClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        };

        // pre-load all of our web URLS here
        // this is going to be pretty memory intensive!
        // optimize this by only loading comics for the right day (today)
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        int i = 0;
        for (ComicContent.ComicWebsiteItem comic : ComicContent.ITEMS) {
            // only pre-load comics for today day of week!
            if(comic.days.daysOfWeek[day] == true) {
                webViews[i] = new WebView(this);
                webViews[i].setWebViewClient(ourClient);
                webViews[i].loadUrl(comic.content);
            }
            i++;
        }


    }

    /**
     * Callback method from {@link ComicListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ComicDetailFragment.ARG_ITEM_ID, id);
            ComicDetailFragment fragment = new ComicDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.comic_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ComicDetailActivity.class);
            detailIntent.putExtra(ComicDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
