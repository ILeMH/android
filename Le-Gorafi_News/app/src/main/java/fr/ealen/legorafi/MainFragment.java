package fr.ealen.legorafi;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class MainFragment extends Fragment {

    private XMLAsyncTask _task = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void onViewCreated (View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView rv = (RecyclerView) view.findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        RSSAdapter adapter = new RSSAdapter((RSSAdapter.URLLoader)getActivity());
        rv.setAdapter(adapter);

        _task = new XMLAsyncTask(adapter);
        _task.execute("https://fr.wikipedia.org/w/api.php?hidebots=1&days=7&limit=50&hideWikibase=1&action=feedrecentchanges&feedformat=rss");
    }

    public void onDestroy(){
        super.onDestroy();

        if(_task !=null){
            _task.cancel(true);
        }
    }
}
