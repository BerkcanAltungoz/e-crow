import axios from "axios";

export default class StatusService{
    getAll() {
        return axios.get("http://localhost:8080/api/status/getAll")
    }

    getById(id) {
        return axios.get(`http://localhost:8080/api/status/getById?id=${id}`)
    }

    getByName(name){
        return axios.get(`http://localhost:8080/api/status/getByName?name=${name}`)
    }
}