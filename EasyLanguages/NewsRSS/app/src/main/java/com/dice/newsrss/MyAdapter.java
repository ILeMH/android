package com.dice.newsrss;

/**
 * Created by Alexandre on 20/01/2017.
 */

        import android.app.Activity;
        import android.content.Intent;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.oc.rss.fake.FakeNews;
        import com.oc.rss.fake.FakeNewsList;

        import java.util.List;

        import static android.support.v4.content.ContextCompat.startActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final List<FakeNews> list = FakeNewsList.all;

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FakeNews pair = list.get(position);
        holder.display(pair);
    }
    /*
     * Class interne ViewHolder : spécificité d'une cellule
     */

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private FakeNews fn;

        public MyViewHolder(final View itemView) {
            super(itemView);

            title = ((TextView) itemView.findViewById(R.id.titleName));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), showHtml.class);
                    intent.putExtra("titre", fn.title);
                    intent.putExtra("annonce", fn.htmlContent);
                    startActivity(itemView.getContext(), intent, null);
                }
            });
        }

        public void display(FakeNews pair) {
            fn = pair;
            title.setText(pair.title);
        }
    }
}
