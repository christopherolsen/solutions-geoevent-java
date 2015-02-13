package com.esri.geoevent.solutions.adapter.webeoc;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import com.esri.ges.adapter.AdapterDefinition;
import com.esri.ges.adapter.InboundAdapterBase;
import com.esri.ges.core.component.ComponentException;
import com.esri.ges.core.geoevent.DefaultFieldDefinition;
import com.esri.ges.core.geoevent.FieldDefinition;
import com.esri.ges.core.geoevent.FieldType;
import com.esri.ges.core.geoevent.GeoEvent;
import com.esri.ges.core.geoevent.GeoEventDefinition;
import com.esri.ges.core.validation.ValidationException;
import com.esri.ges.manager.geoeventdefinition.GeoEventDefinitionManager;

public class WebEOCInboundAdapter extends InboundAdapterBase {
	private String eventDef;
	private Boolean useGeocoding;
	private String geocodeService = null;
	private String geocodeField = null;
	GeoEventDefinitionManager manager;
	public WebEOCInboundAdapter(AdapterDefinition definition)
			throws ComponentException {
		super(definition);
	}
	
	@Override 
	public void afterPropertiesSet()
	{
		try
		{
			eventDef = properties.get("geoeventDefinition").getValueAsString();
			useGeocoding = (Boolean)properties.get("useGeocoding").getValue();
			if(!properties.get("").getValueAsString().isEmpty())
			{
				geocodeService = properties.get("geocodeService").getValueAsString();
			}
			if(!properties.get("geoCodeField").getValueAsString().isEmpty())
			{
				geocodeField = properties.get("geocodeField").getValueAsString();
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Override
	public synchronized void validate() throws ValidationException
	{
		manager = geoEventCreator.getGeoEventDefinitionManager();
		if(manager.searchGeoEventDefinitionByName(eventDef)==null)
		{
			ValidationException e = new ValidationException("Cannot locate Geoevent definition");
			throw(e);
		}
		if(useGeocoding)
		{
			if(geocodeService == null || geocodeField == null )
			{
				ValidationException e = new ValidationException("Geocode Service and Geocode Field must not be empty.");
				throw(e);
			}
		}
	}

	@Override
	protected GeoEvent adapt(ByteBuffer buffer, String channelId)
	{
		// Don't need to implement this class because we are overriding the base class's implementation of the receive() function, which prevents this method from being called.
		return null;
	}
	
	@Override
	public void receive(ByteBuffer buffer, String channelId)
	{
	
	}

}
