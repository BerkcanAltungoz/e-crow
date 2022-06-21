import axios from "axios";

export default class TownService{
    getAll() {
        axios.get("http://localhost:8080/api/town/getAll")
    }

    getById(id) {
        axios.get(`http://localhost:8080/api/town/getById?id=${id}`)
    }

    getByCityId(cityId){
        axios.get(`http://localhost:8080/api/town/getByFkCityId?cityId=${cityId}`)
    }
}