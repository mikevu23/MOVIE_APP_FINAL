package ca.bcit.movieappfinal;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    private final List<String> titleArray;

    private final List<String> descriptionArray;

    private final List<String> urlArray;

    public CustomListAdapter(Activity context, List<String> titleArray, List<String> descriptionArray, List<String> urlArray){

        super(context,R.layout.listview_row , titleArray);
        this.context = context;
        this.titleArray = titleArray;
        this.descriptionArray = descriptionArray;
        this.urlArray = urlArray;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView titleTextField = (TextView) rowView.findViewById(R.id.listTitle);
        TextView descriptionTextField = (TextView) rowView.findViewById(R.id.listDescription);
        TextView urlTextField = rowView.findViewById(R.id.listURL);
        //this code sets the values of the objects to values from the arrays

        titleTextField.setText(titleArray.get(position));
        descriptionTextField.setText(descriptionArray.get(position));
        urlTextField.setText(urlArray.get(position));

        return rowView;

    };
}
