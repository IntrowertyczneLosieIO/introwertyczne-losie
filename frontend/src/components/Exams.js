import React from "react";
import DataTable from "./dashboard/DataTable";
import {Row} from "react-bootstrap";
import Sidenav from "./dashboard/Sidenav";

class Exams extends React.Component {
    render() {
        let values = ["id", "name", "major", "startDate", "endDate", "recruitmentCycleId"];
        let headers = ["Id", "Nazwa", "Kierunek", "Data rozpoczęcia", "Data zakończenia", "Cykl"];

        return (
            <Row className={"no-gutters grid-container"}>
                <div className={"custom-sidenav-bg rounded sidebar"}>
                    <Sidenav objects={this.props.objects}/>
                </div>
                <div className={"border rounded pl-4 pt-4 pr-4 main-content"}>
                    <h2 className={"pb-4 font-weight-light custom-font"}>Egzaminy</h2>
                    <DataTable tableHeader={headers} tableValues={values} name={"Exams"} mapping={"/all-exams"}/>
                </div>
            </Row>
        );
    }
}

export default Exams;