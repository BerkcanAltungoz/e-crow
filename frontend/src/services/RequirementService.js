import axios from "axios";

export default class RequirementService{
    getAll() {
        axios.get("http://localhost:8080/api/paymentMethod/getAll")
    }

    getById(id) {
        axios.get(`http://localhost:8080/api/paymentMethod/getById?id=${id}`)
    }

    getByTransactionId(transactionId){
        axios.get(`http://localhost:8080/api/requirement/getByFkTransactionId?itemTransactionId=${transactionId}`)
    }

    deleteById(id) {
        axios.delete(`http://localhost:8080/api/requirement/deleteById?id=${id}`)
    }

    add(values){
        axios.post("http://localhost:8080/api/requirement/add", values)
    }

    updateSatisfied(values){
        axios.patch("http://localhost:8080/api/requirement/updateSatisfied", values)
    }

    updateSatisfiedTrue(id){
        axios.patch(`http://localhost:8080/api/requirement/updateSatisfiedTrue?id=${id}`)
    }
}