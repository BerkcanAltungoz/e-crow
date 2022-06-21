import axios from "axios";

export default class CityService{

    getAll(){
        axios.get("http://localhost:8080/api/city/getAll")
    }

    getById(id){
        axios.get(`http://localhost:8080/api/city/getById?id=${id}`)
    }

    getByName(name){
        axios.get(`http://localhost:8080/api/city/getByName?name=${name}`)
    }
}