package ro.pub.cs.systems.eim.lab03.phonedialer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import ro.pub.cs.systems.eim.lab03.phonedialer.general.Constants;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class ContactsManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
        
        Button available, save, cancel;
        
        EditText phoneEditText = (EditText) findViewById(R.id.telefon);
        
        Intent intent = getIntent();
        if (intent != null) {
          String phone = intent.getStringExtra("ro.pub.cs.systems.pdsd.lab04.contactsmanager.PHONE_NUMBER_KEY");
          if (phone != null) {
            phoneEditText.setText(phone);
          } else {
            Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
          }
        } 
        
        available = (Button)findViewById(R.id.available);
        
        available.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LinearLayout secondLayout;
				
				Button changeName;
				changeName = (Button)findViewById(R.id.available);
				
				secondLayout = (LinearLayout) findViewById(R.id.linearLayout2);
				if(secondLayout.getVisibility() == View.VISIBLE) {
					changeName.setText("Hide Additional Fields");
					secondLayout.setVisibility(View.GONE);
				} else {
					changeName.setText("View Additional Fields");
					secondLayout.setVisibility(View.VISIBLE);
				}
			}
		});
        
        
        save = (Button)findViewById(R.id.save);
        
        save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
				intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
				
				EditText name, phone, email, address, jobTitle, company, website, im;
				
				name = (EditText)findViewById(R.id.nume);
				phone = (EditText)findViewById(R.id.telefon);
				email = (EditText)findViewById(R.id.email);
				address = (EditText)findViewById(R.id.adresa);
				jobTitle = (EditText)findViewById(R.id.pozitie);
				company = (EditText)findViewById(R.id.companie);
				website = (EditText)findViewById(R.id.site);
				im = (EditText)findViewById(R.id.identificator);
			
				
				if (name.getText().toString() != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
				}
				if (phone.getText().toString() != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText().toString());
				}
				if (email.getText().toString() != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText().toString());
				}
				if (address.getText().toString() != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText().toString());
				}
				if (jobTitle.getText().toString() != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle.getText().toString());
				}
				if (company.getText().toString() != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText().toString());
				}
				ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
				if (website.getText().toString() != null) {
				  ContentValues websiteRow = new ContentValues();
				  websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
				  websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website.getText().toString());
				  contactData.add(websiteRow);
				}
				if (im.getText().toString() != null) {
				  ContentValues imRow = new ContentValues();
				  imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
				  imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im.getText().toString());
				  contactData.add(imRow);
				}
				intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
				startActivity(intent);
				startActivityForResult(intent, Constants.CONTACTS_MANAGER_REQUEST_CODE);
			}
		});
        
        cancel = (Button)findViewById(R.id.cancel);
        
        cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				setResult(Activity.RESULT_CANCELED, new Intent());
			}
		});
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	switch(requestCode) {
    	  case 404:
    	    setResult(resultCode, new Intent());
    	    finish();
    	    break;
    	  }
    	}
    
}
