package com.hunterdavis.comics.content;

import android.util.Log;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ComicContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<ComicWebsiteItem> ITEMS = new ArrayList<ComicWebsiteItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, ComicWebsiteItem> ITEM_MAP = new HashMap<String, ComicWebsiteItem>();

    static {
        // Add 3 sample items.
        addItem(new ComicWebsiteItem("1","Wigu", "http://wigucomics.com/index.php", DaysOfWeekComicUpdates.getTuesThursComicDays()));
        addItem(new ComicWebsiteItem("2","XKCD", "http://xkcd.com", DaysOfWeekComicUpdates.getWeekdaysComicDays()));
        addItem(new ComicWebsiteItem("3","Wapsi Square", "http://wapsisquare.com", DaysOfWeekComicUpdates.getMWFComicDays()));
        addItem(new ComicWebsiteItem("4","QC", "http://questionablecontent.net", DaysOfWeekComicUpdates.getMWFComicDays()));
        addItem(new ComicWebsiteItem("5","PVP", "http://pvponline.com", DaysOfWeekComicUpdates.getMWFComicDays()));
        addItem(new ComicWebsiteItem("6","SMBC", "http://smbc-comics.com", DaysOfWeekComicUpdates.getWeekdaysComicDays()));
        addItem(new ComicWebsiteItem("7","Penny Arcade", "http://penny-arcade.com/comic/", DaysOfWeekComicUpdates.getWeekdaysComicDays()));
        addItem(new ComicWebsiteItem("8","Menage-A-3", "http://ma3comic.com", DaysOfWeekComicUpdates.getWeekdaysComicDays()));
        addItem(new ComicWebsiteItem("9","Diesel Sweeties", "http://dieselsweeties.com", DaysOfWeekComicUpdates.getWeekdaysComicDays()));
        addItem(new ComicWebsiteItem("10","CAD", "http://cad-comic.com", DaysOfWeekComicUpdates.getMWFComicDays()));
        addItem(new ComicWebsiteItem("11","Amazing Super Powers", "http://amazingsuperpowers.com", DaysOfWeekComicUpdates.getWeekdaysComicDays()));
        addItem(new ComicWebsiteItem("12","The Trenches", "http://trenchescomic.com", DaysOfWeekComicUpdates.getTuesThursComicDays()));
        addItem(new ComicWebsiteItem("13","Overcompensating", "http://overcompensating.com", DaysOfWeekComicUpdates.getMWFComicDays()));
        addItem(new ComicWebsiteItem("14","Table Titans", "http://tabletitans.com/comic/", DaysOfWeekComicUpdates.getTuesThursComicDays()));
        addItem(new ComicWebsiteItem("15","Camp WEDONWANCHA", "http://campcomic.com", new DaysOfWeekComicUpdates(false,false,true,false,false,true,false)));
        addItem(new ComicWebsiteItem("16","Alice Grove", "http://alicegrove.com", DaysOfWeekComicUpdates.getWeekdaysComicDays()));
        addItem(new ComicWebsiteItem("17","Tumble Dry Comics", "http://tumbledrycomics.com", DaysOfWeekComicUpdates.getWeekdaysComicDays()));
        addItem(new ComicWebsiteItem("18","Poorly Drawn Lines", "http://poorlydrawnlines.com", DaysOfWeekComicUpdates.getWeekdaysComicDays()));
    }

    private static void addItem(ComicWebsiteItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ComicWebsiteItem {
        public String id;
        public String name;
        public String content;
        public DaysOfWeekComicUpdates days;
        public WebView preLoadWebView;

        public ComicWebsiteItem(String id, String name,String content, DaysOfWeekComicUpdates days) {
            this.id = id;
            this.content = content;
            this.days = days;
            this.name = name;
        }

        public boolean doesComicComeOutToday() {
            Calendar calendar = Calendar.getInstance();

            // day is 1 indexed, but we're zero indexed
            int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;

            return days.daysOfWeek[day];
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class DaysOfWeekComicUpdates {
        public boolean daysOfWeek[] = new boolean[7];

        public DaysOfWeekComicUpdates(boolean su, boolean m, boolean t, boolean w, boolean th, boolean f, boolean sa) {
            daysOfWeek[0] = su;
            daysOfWeek[1] = m;
            daysOfWeek[2] = t;
            daysOfWeek[3] = w;
            daysOfWeek[4] = th;
            daysOfWeek[5] = f;
            daysOfWeek[6] = sa;
        }

        public static DaysOfWeekComicUpdates getMWFComicDays() {
            return new DaysOfWeekComicUpdates(false,true,false,true,false,true,false);
        }

        public static DaysOfWeekComicUpdates getWeekdaysComicDays() {
            return new DaysOfWeekComicUpdates(false,true,true,true,true,true,false);
        }

        public static DaysOfWeekComicUpdates getTuesThursComicDays() {
            return new DaysOfWeekComicUpdates(false,false,true,false,true,false,false);
        }

        public static DaysOfWeekComicUpdates getEveryDayComicDays() {
            return new DaysOfWeekComicUpdates(true,true,true,true,true,true,true);
        }


    }
}
