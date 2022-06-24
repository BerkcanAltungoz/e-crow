import axios from "axios";

export default class TownService{
    getAll() {
        return axios.get("http://localhost:8080/api/town/getAll")
    }

    getById(id) {
        return axios.get(`http://localhost:8080/api/town/getById?id=${id}`)
    }

    getByCityId(cityId){
        return axios.get(`http://localhost:8080/api/town/getByFkCityId?cityId=${cityId}`)
    }
}