import axios from "axios";

export default class RequirementService{
    getAll() {
        return axios.get("http://localhost:8080/api/paymentMethod/getAll")
    }

    getById(id) {
        return axios.get(`http://localhost:8080/api/paymentMethod/getById?id=${id}`)
    }

    getByTransactionId(transactionId){
        return axios.get(`http://localhost:8080/api/requirement/getByFkTransactionId?itemTransactionId=${transactionId}`)
    }

    deleteById(id) {
        return axios.delete(`http://localhost:8080/api/requirement/deleteById?id=${id}`)
    }

    add(values){
        return axios.post("http://localhost:8080/api/requirement/add", values)
    }

    updateSatisfied(values){
        return axios.patch("http://localhost:8080/api/requirement/updateSatisfied", values)
    }

    updateSatisfiedTrue(id){
        return axios.patch(`http://localhost:8080/api/requirement/updateSatisfiedTrue?id=${id}`)
    }
}