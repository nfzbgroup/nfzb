package com.wonders.fzb.platform.webservice.bo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.fzb.platform.webservice.kit.WSConvertKit;

import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

@XmlRootElement
@SOAPBinding(style = Style.RPC)
public class WebServiceList<T> extends ArrayList<T> implements Serializable {

	private static final long serialVersionUID = -7935341415704333234L;

	// public E get(int index){
	// return innerList.get(index);
	// }

	public T getFirst() {
		return size() > 0 ? get(0) : null;
	}

	// public void add(E e){
	// innerList.add(e);
	// }

	// public int size(){
	// return innerList.size();
	// }

	public String toXML() {
		XMLSerializer xmlSerializer = new XMLSerializer();
		return xmlSerializer.write(JSONArray.fromObject(this));
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		if (size() > 0) {
			try {
				String className = getFirst().getClass().getSimpleName();
				String packageName = getFirst().getClass().getPackage().getName();
				buffer.append("<Array entityName=\"" + className + "\" size=\"" + size()
						+ "\" namespace=\"" + packageName + "\">");
				for (T t : this) {
					buffer.append(WSConvertKit.convertXML(t));
				}
				buffer.append("</Array>");
			} catch (Exception e) {
				buffer.append("<error>");
			}
		} else
			buffer.append("<empty />");
		return buffer.toString();
	}
}