import React from "react";
import DataTable from "./dashboard/DataTable";
import {Row} from "react-bootstrap";
import Sidenav from "./dashboard/Sidenav";

class Recruitments extends React.Component {
    render() {
        let values = ["id", "acronym", "year", "semester", "recruitmentStatus"];
        let headers = ["Id", "Nazwa", "Rok", "Semestr", "Status rekrutacji"];

        return (
            <Row className={"no-gutters grid-container"}>
                <div className={"custom-sidenav-bg rounded sidebar"}>
                    <Sidenav objects={this.props.objects}/>
                </div>
                <div className={"border rounded pl-4 pt-4 pr-4 main-content"}>
                    <h2 className={"pb-4 font-weight-light custom-font"}>Sale</h2>
                    <DataTable tableHeader={headers} tableValues={values} name={"Recruitments"} mapping={"/all-recruitments"}/>
                </div>
            </Row>
        );
    }
}

export default Recruitments;