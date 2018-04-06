/**
 * 
 */
package com.power.common.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Area;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月26日
 */

public class IchnographyEntity  extends DataEntity<IchnographyEntity>{

    private static final long serialVersionUID = 1L;
        private Area area;
        private String name;
        private String url;
        private String   ichnographyId;
        public Area getArea() {
            return area;
        }
        public void setArea(Area area) {
            this.area = area;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public String getIchnographyId() {
            return ichnographyId;
        }
        public void setIchnographyId(String ichnographyId) {
            this.ichnographyId = ichnographyId;
        }
        
}
