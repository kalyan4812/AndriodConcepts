package com.example.andriodconcept.WebServices;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class SOAPRequest  {
    public String requestcurrencybyiso(String Isocode){

        String URL ="http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?wsdl";
          String SOAP_ACTION = "http://webservices.oorsprong.org/websamples.countryinfo/CountryName";
        String NAMESPACE = "http://www.oorsprong.org/websamples.countryinfo";
         String METHOD_NAME = "CountryName";
         String results="";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("sCountryISOCode",Isocode);
        // if you want to add other parameter like Iso code repeat above line by new parameter

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            SoapObject result = (SoapObject) envelope.bodyIn;
            if (result != null) {
                results = result.getProperty(0).toString();
            } else {
                results = "NO DATA FOUND";
            }
        }
            catch (Exception e) {
            System.out.println("Error"+e);
        }
        return results;
    }
}
