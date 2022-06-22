import axios from "axios";

export default class CityService{

    getAll(){
        return axios.get("http://localhost:8080/api/city/getAll")
    }

    getById(id){
        return axios.get(`http://localhost:8080/api/city/getById?id=${id}`)
    }

    getByName(name){
        return axios.get(`http://localhost:8080/api/city/getByName?name=${name}`)
    }
}