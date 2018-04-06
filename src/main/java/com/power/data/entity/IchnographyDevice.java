/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.data.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 平面图设备Entity
 * @author zhanglg
 * @version 2017-06-10
 */
public class IchnographyDevice extends DataEntity<IchnographyDevice> {
	
	private static final long serialVersionUID = 1L;
	private String ichnographyName;		// ichnography_name
	private String sbbName;		// sbb_name
	private String ichnographyId;		// ichnography_id
	private String sbbId;		// sbb_id
	private String xAxesStart;		// x_axes_start
	private String yAxesStart;		// y_axes_start
	private String xAxesEnd;		// x_axes_end
	private String yAxesEnd;		// y_axes_end
	private String url;
	private String longitude;
    private String latitude;
    private String sbbType;
    private String installDate;
	public IchnographyDevice() {
		super();
	}

	public IchnographyDevice(String id){
		super(id);
	}

	@Length(min=0, max=255, message="ichnography_name长度必须介于 0 和 255 之间")
	public String getIchnographyName() {
		return ichnographyName;
	}

	public void setIchnographyName(String ichnographyName) {
		this.ichnographyName = ichnographyName;
	}
	
	@Length(min=0, max=255, message="sbb_name长度必须介于 0 和 255 之间")
	public String getSbbName() {
		return sbbName;
	}

	public void setSbbName(String sbbName) {
		this.sbbName = sbbName;
	}
	
	@Length(min=1, max=255, message="ichnography_id长度必须介于 1 和 255 之间")
	public String getIchnographyId() {
		return ichnographyId;
	}

	public void setIchnographyId(String ichnographyId) {
		this.ichnographyId = ichnographyId;
	}
	
	@Length(min=1, max=255, message="sbb_id长度必须介于 1 和 255 之间")
	public String getSbbId() {
		return sbbId;
	}

	public void setSbbId(String sbbId) {
		this.sbbId = sbbId;
	}
	

    public String getxAxesStart() {
        return xAxesStart;
    }

    public void setxAxesStart(String xAxesStart) {
        this.xAxesStart = xAxesStart;
    }

    public String getyAxesStart() {
        return yAxesStart;
    }

    public void setyAxesStart(String yAxesStart) {
        this.yAxesStart = yAxesStart;
    }

    public String getxAxesEnd() {
        return xAxesEnd;
    }

    public void setxAxesEnd(String xAxesEnd) {
        this.xAxesEnd = xAxesEnd;
    }

    public String getyAxesEnd() {
        return yAxesEnd;
    }

    public void setyAxesEnd(String yAxesEnd) {
        this.yAxesEnd = yAxesEnd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSbbType() {
        return sbbType;
    }

    public void setSbbType(String sbbType) {
        this.sbbType = sbbType;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }
    
	
}