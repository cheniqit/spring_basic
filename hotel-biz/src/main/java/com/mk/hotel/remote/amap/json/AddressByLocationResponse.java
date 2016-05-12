package com.mk.hotel.remote.amap.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by chenqi on 16/5/12.
 */
public class AddressByLocationResponse {
    private String status;
    private String info;
    private String infocode;
    private Regeocode regeocode;


    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }


    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }


    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }
    public String getInfocode() {
        return infocode;
    }


    public void setRegeocode(Regeocode regeocode) {
        this.regeocode = regeocode;
    }
    public Regeocode getRegeocode() {
        return regeocode;
    }


    class Neighborhood {

    }

    class Building {

        private String name;
        private String type;


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }

    }


    class BusinessAreas {

        private String location;
        private String name;
        private String id;


        public void setLocation(String location) {
            this.location = location;
        }
        public String getLocation() {
            return location;
        }


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

    }

    class AddressComponent {

        private String country;
        private String province;
        private String city;
        private String citycode;
        private String district;
        private String adcode;
        private String township;
        private String towncode;
        private Neighborhood neighborhood;
        private Building building;
        @JSONField(name ="streetNumber")
        private StreetNumber streetnumber;
        @JSONField(name ="businessAreas")
        private List<BusinessAreas> businessareas;


        public void setCountry(String country) {
            this.country = country;
        }
        public String getCountry() {
            return country;
        }


        public void setProvince(String province) {
            this.province = province;
        }
        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }
        public String getCitycode() {
            return citycode;
        }


        public void setDistrict(String district) {
            this.district = district;
        }
        public String getDistrict() {
            return district;
        }


        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }
        public String getAdcode() {
            return adcode;
        }


        public void setTownship(String township) {
            this.township = township;
        }
        public String getTownship() {
            return township;
        }


        public void setTowncode(String towncode) {
            this.towncode = towncode;
        }
        public String getTowncode() {
            return towncode;
        }


        public void setNeighborhood(Neighborhood neighborhood) {
            this.neighborhood = neighborhood;
        }
        public Neighborhood getNeighborhood() {
            return neighborhood;
        }


        public void setBuilding(Building building) {
            this.building = building;
        }
        public Building getBuilding() {
            return building;
        }


        public void setStreetnumber(StreetNumber streetnumber) {
            this.streetnumber = streetnumber;
        }
        public StreetNumber getStreetnumber() {
            return streetnumber;
        }


        public void setBusinessareas(List<BusinessAreas> businessareas) {
            this.businessareas = businessareas;
        }
        public List<BusinessAreas> getBusinessareas() {
            return businessareas;
        }

    }

    class StreetNumber {

        private String street;
        private String number;
        private String location;
        private String direction;
        private String distance;


        public void setStreet(String street) {
            this.street = street;
        }
        public String getStreet() {
            return street;
        }


        public void setNumber(String number) {
            this.number = number;
        }
        public String getNumber() {
            return number;
        }


        public void setLocation(String location) {
            this.location = location;
        }
        public String getLocation() {
            return location;
        }


        public void setDirection(String direction) {
            this.direction = direction;
        }
        public String getDirection() {
            return direction;
        }


        public void setDistance(String distance) {
            this.distance = distance;
        }
        public String getDistance() {
            return distance;
        }

    }

    class Pois {

        private String id;
        private String name;
        private String type;
        private String tel;
        private String direction;
        private String distance;
        private String location;
        private String address;
        private String poiweight;
        private String businessarea;


        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
        public String getDirection() {
            return direction;
        }


        public void setDistance(String distance) {
            this.distance = distance;
        }
        public String getDistance() {
            return distance;
        }


        public void setLocation(String location) {
            this.location = location;
        }
        public String getLocation() {
            return location;
        }


        public void setAddress(String address) {
            this.address = address;
        }
        public String getAddress() {
            return address;
        }


        public void setPoiweight(String poiweight) {
            this.poiweight = poiweight;
        }
        public String getPoiweight() {
            return poiweight;
        }


        public void setBusinessarea(String businessarea) {
            this.businessarea = businessarea;
        }
        public String getBusinessarea() {
            return businessarea;
        }

    }

    class Roads {

        private String id;
        private String name;
        private String direction;
        private String distance;
        private String location;


        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


        public void setDirection(String direction) {
            this.direction = direction;
        }
        public String getDirection() {
            return direction;
        }


        public void setDistance(String distance) {
            this.distance = distance;
        }
        public String getDistance() {
            return distance;
        }


        public void setLocation(String location) {
            this.location = location;
        }
        public String getLocation() {
            return location;
        }

    }

    class Roadinters {

        private String direction;
        private String distance;
        private String location;
        @JSONField(name ="first_id")
        private String firstId;
        @JSONField(name ="first_name")
        private String firstName;
        @JSONField(name ="second_id")
        private String secondId;
        @JSONField(name ="second_name")
        private String secondName;


        public void setDirection(String direction) {
            this.direction = direction;
        }
        public String getDirection() {
            return direction;
        }


        public void setDistance(String distance) {
            this.distance = distance;
        }
        public String getDistance() {
            return distance;
        }


        public void setLocation(String location) {
            this.location = location;
        }
        public String getLocation() {
            return location;
        }


        public void setFirstId(String firstId) {
            this.firstId = firstId;
        }
        public String getFirstId() {
            return firstId;
        }


        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getFirstName() {
            return firstName;
        }


        public void setSecondId(String secondId) {
            this.secondId = secondId;
        }
        public String getSecondId() {
            return secondId;
        }


        public void setSecondName(String secondName) {
            this.secondName = secondName;
        }
        public String getSecondName() {
            return secondName;
        }

    }

    class Aois {

        private String id;
        private String name;
        private String adcode;
        private String location;
        private String area;
        private String type;


        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }
        public String getAdcode() {
            return adcode;
        }


        public void setLocation(String location) {
            this.location = location;
        }
        public String getLocation() {
            return location;
        }


        public void setArea(String area) {
            this.area = area;
        }
        public String getArea() {
            return area;
        }


        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }

    }

    class Regeocode {

        @JSONField(name ="formatted_address")
        private String formattedAddress;
        @JSONField(name ="addressComponent")
        private AddressComponent addressComponent;
        private List<Pois> pois;
        private List<Roads> roads;
        private List<Roadinters> roadinters;
        private List<Aois> aois;


        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }
        public String getFormattedAddress() {
            return formattedAddress;
        }


        public void setAddresscomponent(AddressComponent addresscomponent) {
            this.addressComponent = addresscomponent;
        }
        public AddressComponent getAddresscomponent() {
            return addressComponent;
        }


        public void setPois(List<Pois> pois) {
            this.pois = pois;
        }
        public List<Pois> getPois() {
            return pois;
        }


        public void setRoads(List<Roads> roads) {
            this.roads = roads;
        }
        public List<Roads> getRoads() {
            return roads;
        }


        public void setRoadinters(List<Roadinters> roadinters) {
            this.roadinters = roadinters;
        }
        public List<Roadinters> getRoadinters() {
            return roadinters;
        }


        public void setAois(List<Aois> aois) {
            this.aois = aois;
        }
        public List<Aois> getAois() {
            return aois;
        }

    }
}
