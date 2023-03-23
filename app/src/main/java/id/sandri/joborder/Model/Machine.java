package id.sandri.joborder.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Machine {
    private String tgl_dd;
    private String no_ok;
    private String article;
    private String bahan;
    private String running_meter;
    private String working_time;
    private String prep_time;
    private String break_time;
    private String down_time;
    private String working_hours;
    private String start;
    private String finish;
    private String comment;

    public Machine(JSONObject object){
        try{
            String tgl_dd = object.getString("tgl_dd");
            String no_ok = object.getString("no_ok");
            String article = object.getString("article");
            String bahan = object.getString("bahan");
            String running_meter = object.getString("running_meter");
            String working_time = object.getString("working_time");
            String prep_time = object.getString("prep_time");
            String break_time = object.getString("break_time");
            String down_time = object.getString("down_time");
            String working_hours = object.getString("working_hours");
            String start = object.getString("start");
            String finish = object.getString("finish");
            String comment = object.getString("comment");

            this.tgl_dd = tgl_dd;
            this.no_ok = no_ok;
            this.article = article;
            this.bahan = bahan;
            this.running_meter = running_meter;
            this.working_time = working_time;
            this.prep_time = prep_time;
            this.break_time = break_time;
            this.down_time = down_time;
            this.working_hours = working_hours;
            this.start = start;
            this.finish = finish;
            this.comment = comment;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTgl_dd() {
        return tgl_dd;
    }

    public void setTgl_dd(String tgl_dd) {
        this.tgl_dd = tgl_dd;
    }

    public String getNo_ok() {
        return no_ok;
    }

    public void setNo_ok(String no_ok) {
        this.no_ok = no_ok;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getRunning_meter() {
        return running_meter;
    }

    public void setRunning_meter(String running_meter) {
        this.running_meter = running_meter;
    }

    public String getWorking_time() {
        return working_time;
    }

    public void setWorking_time(String working_time) {
        this.working_time = working_time;
    }

    public String getPrep_time() {
        return prep_time;
    }

    public void setPrep_time(String prep_time) {
        this.prep_time = prep_time;
    }

    public String getBreak_time() {
        return break_time;
    }

    public void setBreak_time(String break_time) {
        this.break_time = break_time;
    }

    public String getDown_time() {
        return down_time;
    }

    public void setDown_time(String down_time) {
        this.down_time = down_time;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
