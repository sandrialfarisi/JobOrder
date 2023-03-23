package id.sandri.joborder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.sandri.joborder.DetailActivity;
import id.sandri.joborder.Model.Machine;
import id.sandri.joborder.R;

public class MachineAdapter extends RecyclerView.Adapter<MachineAdapter.ViewHolder> {
    Context context;
    ArrayList<Machine> listMachine;

    public MachineAdapter(Context context) {
        this.context = context;
    }
    public ArrayList<Machine> getListMachine() {
        return listMachine;
    }
    public void setListMachine(ArrayList<Machine> listMachine) {
        this.listMachine = listMachine;
    }

    @NonNull
    @Override
    public MachineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cardview_machine,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MachineAdapter.ViewHolder holder, final int position) {
        if (listMachine.get(position).getTgl_dd().equals("null") || listMachine.get(position).getTgl_dd().equals("0")){
            holder.tgl_dd.setText("-");
        }else{
            holder.tgl_dd.setText(listMachine.get(position).getTgl_dd());
        }

        if (listMachine.get(position).getNo_ok().equals("null") || listMachine.get(position).getNo_ok().equals("0")){
            holder.no_ok.setText("-");
        }else {
            holder.no_ok.setText(listMachine.get(position).getNo_ok());
        }

        if (listMachine.get(position).getArticle().equals("null") || listMachine.get(position).getNo_ok().equals("0")){
            holder.article.setText("-");
        }else {
            holder.article.setText(listMachine.get(position).getArticle());
        }

        if (listMachine.get(position).getBahan().equals("null") || listMachine.get(position).getBahan().equals("0")){
            holder.bahan.setText("-");
        }else {
            holder.bahan.setText(listMachine.get(position).getBahan());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = v.getContext();
                Intent i = new Intent(mContext, DetailActivity.class);
                i.putExtra("tgl_dd",listMachine.get(position).getTgl_dd());
                i.putExtra("no_ok",listMachine.get(position).getNo_ok());
                i.putExtra("article",listMachine.get(position).getArticle());
                i.putExtra("bahan",listMachine.get(position).getBahan());
                i.putExtra("running_meter",listMachine.get(position).getRunning_meter());
                i.putExtra("working_time",listMachine.get(position).getWorking_time());
                i.putExtra("prep_time",listMachine.get(position).getPrep_time());
                i.putExtra("break_time",listMachine.get(position).getBreak_time());
                i.putExtra("down_time",listMachine.get(position).getDown_time());
                i.putExtra("working_hours",listMachine.get(position).getWorking_hours());
                i.putExtra("start",listMachine.get(position).getStart());
                i.putExtra("finish",listMachine.get(position).getFinish());
                i.putExtra("comment",listMachine.get(position).getComment());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListMachine().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tgl_dd, no_ok, article, bahan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tgl_dd = itemView.findViewById(R.id.tv_tgl_dd);
            no_ok = itemView.findViewById(R.id.tv_no_ok);
            article = itemView.findViewById(R.id.tv_article);
            bahan = itemView.findViewById(R.id.tv_bahan);
        }
    }

    public void filterList(ArrayList<Machine> filteredList){
        listMachine = new ArrayList<>();
        listMachine.addAll(filteredList);
        notifyDataSetChanged();
    }
}
