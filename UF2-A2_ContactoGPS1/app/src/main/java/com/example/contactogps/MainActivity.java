package com.example.contactogps;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	private LocationManager locManager;
	private Button btnActivar;
	private Button btnDesactivar;
	private TextView txtLatitud;
	private TextView txtLongitud;
	private TextView txtPrecision;
	private TextView txtEstado;
	private LocationListener locListener; 
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtLatitud = (TextView) findViewById(R.id.txtLatitud);
        txtLongitud = (TextView) findViewById(R.id.txtLongitud);
        txtPrecision = (TextView) findViewById(R.id.txtPrecision);
        txtEstado= (TextView) findViewById(R.id.txtEstado);
        
        // Programamos el boton de activar para empezar a capturar las posisiones
        // En caso de que no este activo el GPS le mostraremos mensaje al usuario
        
        btnActivar = (Button) findViewById(R.id.btnActivar);
        btnActivar.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Obtenemos un proveedor de localixzacion del systema
		        locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
				// Miramos como esta el GPS, su estado
		         // OJO!!! Debemos dar permiso en el Manifest para acceder a la localizacion fina y coarse
		        if (locManager.isProviderEnabled(locManager.GPS_PROVIDER)) {
					// Mostramos la ultima localizacion conocida
		        	Location loc = locManager.getLastKnownLocation(locManager.GPS_PROVIDER);
		        	mostrarLocalizacion(loc);
		        	
		        	
		        	// Adem?s programamos el listener para ir capturandod de forma automatica las posiciones del GPS
		        	programarListenerGps();
		        	// enlazamos los eventos del GPS con el listener creado
		        	locManager.requestLocationUpdates(locManager.GPS_PROVIDER, 1000, 0, locListener);
		        	
		        	
				} else {
					Toast.makeText(getApplicationContext(), "GPS Desactivado", Toast.LENGTH_LONG).show();
					
				}
				
		        
		        // Obtenemos la ultima posicion conocida y la mostramops
		       // Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		        
				
			}  // public void onClick(View v) {
			
		});  // btnActivar.setOnClickListener
        
        
        // Programamos el boton de Desactivar
        btnDesactivar =(Button) findViewById(R.id.btnDesactivar);
        btnDesactivar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Hacemos que el listener no "escuche" al GPS
				locManager.removeUpdates(locListener);
				txtEstado.setText("Estado proveedor: Parado Escucha");
			}
		}); // btnDesactivar.setOnClickListener
      
  
        
    }


    public void programarListenerGps(){
		locListener= new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				txtEstado.setText("Estado proveedor: "+status);
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				txtEstado.setText("Proveedor GPS On");
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				txtEstado.setText("Proveedor GPS Off");
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				mostrarLocalizacion(location);
			}
		};
		
    } // ProgramasListener
    
    
    
    
    public void mostrarLocalizacion(Location loc){
    	// Es importante mirar que la localizacion no sea nula por si nunca se ha tenido una localizacion
    	// valdia
    	if (loc != null){
    		txtLatitud.setText("Latitud: "+ String.valueOf(loc.getLatitude()));
    		txtLongitud.setText("Longitud:"+ String.valueOf(loc.getLongitude()));
       		txtPrecision.setText("Altitud:"+ String.valueOf(loc.getAltitude()));
    	} else {
    		txtLatitud.setText("Latitud: (sin dato)");
    		txtLongitud.setText("Longitud: (sin dato)");
    		txtPrecision.setText("Altitud: (sin dato)");
    	}
    }
    
    
    // Desactivem la recollida de dades. AIX? DEPEND de l'aplicaci? 
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	locManager.removeUpdates(locListener);
    }

    // Agafem dades del GPS
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	// locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locListener);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
        
       
    }

    
}  // Class
