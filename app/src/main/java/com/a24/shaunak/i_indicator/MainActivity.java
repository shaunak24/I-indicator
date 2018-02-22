package com.a24.shaunak.i_indicator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<Vertex> nodes = new ArrayList<>();
    private static List<Edge> edges = new ArrayList<>();
    int sourceNo = 0, destNo = 0;
    String summary = "", source = "", destination = "";

    String[] cityNames = {"srinagar", "chandigarh", "delhi", "jaipur", "lucknow",
            "ahmedabad", "mumbai", "bhopal", "bangalore", "chennai",
            "hyderabad", "raipur", "kolkalta", "patna", "dispur"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static void addLane(String laneId, int sourceLocNo, int destLocNo,
                                int distance) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), distance);
        edges.add(lane);
    }

    public void executeAlgorithm(View view) {

        String routeDetails = "";

        TextView sourceName = (TextView) findViewById(R.id.source_text);
        source = sourceName.getText().toString();

        TextView destinationName = (TextView) findViewById(R.id.destination_text);
        destination = destinationName.getText().toString();

        TextView dist = (TextView) findViewById(R.id.distance_text);
        TextView fare = (TextView) findViewById(R.id.fare_text);
        TextView route = (TextView) findViewById(R.id.route_text);

        RadioButton AC = (RadioButton) findViewById(R.id.AC_checkbox);
        RadioButton nonAC = (RadioButton) findViewById(R.id.nonAC_checkbox);

        Vertex location = new Vertex("srinagar", "srinagar");
        nodes.add(location);
        location = new Vertex("chandigarh", "chandigarh");
        nodes.add(location);
        location = new Vertex("delhi", "delhi");
        nodes.add(location);
        location = new Vertex("jaipur", "jaipur");
        nodes.add(location);
        location = new Vertex("lucknow", "lucknow");
        nodes.add(location);
        location = new Vertex("ahmedabad", "ahmedabad");
        nodes.add(location);
        location = new Vertex("mumbai", "mumbai");
        nodes.add(location);
        location = new Vertex("bhopal", "bhopal");
        nodes.add(location);
        location = new Vertex("bangalore", "bangalore");
        nodes.add(location);
        location = new Vertex("chennai", "chennai");
        nodes.add(location);
        location = new Vertex("hyderabad", "hyderabad");
        nodes.add(location);
        location = new Vertex("raipur", "raipur");
        nodes.add(location);
        location = new Vertex("kolkata", "kolkata");
        nodes.add(location);
        location = new Vertex("patna", "patna");
        nodes.add(location);
        location = new Vertex("dispur", "dispur");
        nodes.add(location);

        for (int i = 0; i < cityNames.length; i++) {

            if (source.trim().toLowerCase().equals(cityNames[i]))
                sourceNo = i;

            if (destination.trim().toLowerCase().equals(cityNames[i]))
                destNo = i;
        }

        addLane("", 0, 1, 567);
        addLane("", 1, 0, 567);
        addLane("", 0, 2, 822);
        addLane("", 2, 0, 822);

        addLane("", 1, 3, 521);
        addLane("", 3, 1, 521);
        addLane("", 3, 2, 276);
        addLane("", 2, 3, 276);
        addLane("", 3, 4, 573);
        addLane("", 4, 3, 573);
        addLane("", 3, 11, 1211);
        addLane("", 11, 3, 1211);
        addLane("", 3, 7, 620);
        addLane("", 7, 3, 620);
        addLane("", 3, 5, 653);
        addLane("", 5, 3, 653);

        addLane("", 5, 7, 587);
        addLane("", 7, 5, 587);
        addLane("", 5, 6, 524);
        addLane("", 6, 5, 524);

        addLane("", 6, 7, 775);
        addLane("", 6, 10, 702);
        addLane("", 6, 8, 992);
        addLane("", 7, 6, 775);
        addLane("", 10, 6, 702);
        addLane("", 8, 6, 992);

        addLane("", 8, 10, 569);
        addLane("", 8, 9, 346);
        addLane("", 10, 8, 569);
        addLane("", 9, 8, 346);

        addLane("", 9, 10, 628);
        addLane("", 10, 9, 628);

        addLane("", 10, 7, 850);
        addLane("", 10, 11, 781);
        addLane("", 7, 10, 850);
        addLane("", 11, 10, 781);

        addLane("", 11, 7, 628);
        addLane("", 11, 4, 851);
        addLane("", 11, 13, 736);
        addLane("", 11, 12, 944);
        addLane("", 7, 11, 628);
        addLane("", 4, 11, 851);
        addLane("", 13, 11, 736);
        addLane("", 12, 11, 944);

        addLane("", 12, 13, 580);
        addLane("", 12, 14, 1034);
        addLane("", 13, 12, 580);
        addLane("", 14, 12, 1034);

        addLane("", 13, 4, 536);
        addLane("", 13, 14, 933);
        addLane("", 4, 13, 536);
        addLane("", 14, 13, 933);

        addLane("", 4, 2, 533);
        addLane("", 2, 4, 533);

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(sourceNo));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(destNo));

        path.toArray();
        for (int i = 0; i < path.size(); i++)
            routeDetails += "--> " + path.get(i).toString().toUpperCase() + '\n';

        route.setText(routeDetails);
        summary += "Route : " + '\n' + routeDetails;
        dist.setText("Total distance (in kms.) : " + dijkstra.getShortestDistance(nodes.get(destNo)));
        summary += "Total distance (in kms.) : " + dijkstra.getShortestDistance(nodes.get(destNo)) + '\n';

        if (AC.isChecked()) {
            fare.setText("Total fare : ₹ " + dijkstra.getShortestDistance(nodes.get(destNo)) * 13);
            summary += "Total fare : ₹ " + dijkstra.getShortestDistance(nodes.get(destNo)) * 13 + '\n';
        }
        if (nonAC.isChecked()) {
            fare.setText("Total fare : ₹ " + dijkstra.getShortestDistance(nodes.get(destNo)) * 9);
            summary += "Total fare : ₹ " + dijkstra.getShortestDistance(nodes.get(destNo)) * 9 + '\n';
        }

        summary += "Thank you !!";

    }

    public void booking(View view) {

        EditText email = (EditText) findViewById(R.id.email_id);
        String[] email_id = {email.getText().toString()};

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        CharSequence subject = "Booking for journey from " + source + " to " + destination;
        intent.putExtra(Intent.EXTRA_EMAIL, email_id);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject.toString());
        intent.putExtra(Intent.EXTRA_TEXT, summary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

}

