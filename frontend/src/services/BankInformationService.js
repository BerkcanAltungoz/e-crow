import axios from "axios";

export default class BankInformationService{

    getAll(){
        axios.get("http://localhost:8080/api/bankInformation/getAll")
    }

    getById(id){
        axios.get(`http://localhost:8080/api/bankInformation/getById?id=${id}`)
    }

    getByUserId(userId){
        axios.get(`http://localhost:8080/api/bankInformation/getByFkUserId?userId=${userId}`)
    }

    deleteById(id){
        axios.delete(`http://localhost:8080/api/bankInformation/deleteById?id=${id}`)
    }

    add(values){
        axios.post("http://localhost:8080/api/bankInformation/add", values)
    }

    update(values){
        axios.patch("http://localhost:8080/api/bankInformation/update", values)
    }
}