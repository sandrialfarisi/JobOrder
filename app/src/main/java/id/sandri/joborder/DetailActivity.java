package id.sandri.joborder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_TGL_DD = "tgl_dd";
    public static final String EXTRA_NO_OK = "no_ok";
    public static final String EXTRA_ARTICLE = "article";
    public static final String EXTRA_BAHAN = "bahan";
    public static final String EXTRA_RUNNING_METER = "running_meter";
    public static final String EXTRA_WORKING_TIME = "working_time";
    public static final String EXTRA_PREP_TIME = "prep_time";
    public static final String EXTRA_BREAK_TIME = "break_time";
    public static final String EXTRA_DOWN_TIME = "down_time";
    public static final String EXTRA_WORKING_HOURS = "working_hours";
    public static final String EXTRA_START = "start";
    public static final String EXTRA_FINISH = "finish";
    public static final String EXTRA_COMMENT = "comment";

    String tgl_dd, no_ok, article, bahan, running_meter, working_time, prep_time, break_time, down_time, working_hours, start, finish, comment;

    TextView tvtgl_dd, tvno_ok, tvarticle, tvbahan, tvrunning_meter, tvworking_time, tvprep_time,
    tvbreak_time, tvdown_time, tvworking_hours, tvstart, tvfinish, tvcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvtgl_dd = findViewById(R.id.tv_dtTgl_dd);
        tvno_ok = findViewById(R.id.tv_dtNo_ok);
        tvarticle = findViewById(R.id.tv_dtArticle);
        tvbahan = findViewById(R.id.tv_dtBahan);
        tvrunning_meter = findViewById(R.id.tv_dtRunning_meter);
        tvworking_time = findViewById(R.id.tv_dtWorking_time);
        tvprep_time = findViewById(R.id.tv_dtPrepare_time);
        tvbreak_time = findViewById(R.id.tv_dtBreak_time);
        tvdown_time = findViewById(R.id.tv_dtDown_time);
        tvworking_hours = findViewById(R.id.tv_dtWorking_hours);
        tvstart = findViewById(R.id.tv_dtStart);
        tvfinish = findViewById(R.id.tv_dtFinish);
        tvcomment = findViewById(R.id.tv_dtComment);

        tgl_dd = getIntent().getStringExtra(EXTRA_TGL_DD);
        no_ok = getIntent().getStringExtra(EXTRA_NO_OK);
        article = getIntent().getStringExtra(EXTRA_ARTICLE);
        bahan = getIntent().getStringExtra(EXTRA_BAHAN);
        running_meter = getIntent().getStringExtra(EXTRA_RUNNING_METER);
        working_time = getIntent().getStringExtra(EXTRA_WORKING_TIME);
        prep_time = getIntent().getStringExtra(EXTRA_PREP_TIME);
        break_time = getIntent().getStringExtra(EXTRA_BREAK_TIME);
        down_time = getIntent().getStringExtra(EXTRA_DOWN_TIME);
        working_hours = getIntent().getStringExtra(EXTRA_WORKING_HOURS);
        start = getIntent().getStringExtra(EXTRA_START);
        finish = getIntent().getStringExtra(EXTRA_FINISH);
        comment = getIntent().getStringExtra(EXTRA_COMMENT);

        Log.d("onCreate: ", article);


        if (tgl_dd.equals("null") || tgl_dd.equals("0")){
            tvtgl_dd.setText("-");
        }else {
            tvtgl_dd.setText(tgl_dd);
        }

        if (no_ok.equals("null") || no_ok.equals("0")){
            tvno_ok.setText("-");
        }else {
            tvno_ok.setText(no_ok);
        }

        if (article.equals("null") ||article.equals("0")){
            tvarticle.setText("-");
        }else {
            tvarticle.setText(article);
        }

        if (bahan.equals("null") || bahan.equals("0")){
            tvbahan.setText("-");
        }else{
            tvbahan.setText(bahan);
        }

        if (running_meter.equals("null") || running_meter.equals("0")){
            tvrunning_meter.setText("-");
        }else{
           tvrunning_meter.setText(running_meter);
        }

        if (working_time.equals("null") || working_time.equals("0")){
            tvworking_time.setText("-");
        }else{
            tvworking_time.setText(working_time);
        }

        if (prep_time.equals("null") || prep_time.equals("0")){
            tvprep_time.setText("-");
        }else {
            tvprep_time.setText(prep_time);
        }

        if (break_time.equals("null") || break_time.equals("0")){
            tvbreak_time.setText("-");
        }else {
            tvbreak_time.setText(break_time);
        }

        if (down_time.equals("null") || down_time.equals("0")){
            tvdown_time.setText("-");
        }else {
            tvdown_time.setText(down_time);
        }

        if (working_hours.equals("null") || working_hours.equals("0")){
            tvworking_hours.setText("-");
        }else {
            tvworking_hours.setText(working_hours);
        }

        if (start.equals("null") || start.equals("0")){
            tvstart.setText("-");
        }else {
            tvstart.setText(start);
        }

        if (finish.equals("null") || finish.equals("0")){
            tvfinish.setText("-");
        }else {
            tvfinish.setText(finish);
        }

        if (comment.equals("null") || comment.equals("0")){
            tvcomment.setText("-");
        }else {
            tvcomment.setText(comment);
        }
    }
}
