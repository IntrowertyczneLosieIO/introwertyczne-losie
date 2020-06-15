import React from "react";
import DataTable from "./dashboard/DataTable";
import {Row} from "react-bootstrap";
import Sidenav from "./dashboard/Sidenav";

class Rooms extends React.Component {
    render() {
        let values = ["id", "localization", "number", "recommendedCapacity", "maximalCapacity"];
        let headers = ["Id", "Budynek", "Numer sali", "Rekomendowana pojemność", "Maksymalna pojemność"];

        return (
            <Row className={"no-gutters grid-container"}>
                <div className={"custom-sidenav-bg rounded sidebar"}>
                    <Sidenav objects={this.props.objects}/>
                </div>
                <div className={"border rounded pl-4 pt-4 pr-4 main-content"}>
                    <h2 className={"pb-4 font-weight-light custom-font"}>Sale</h2>
                    <DataTable tableHeader={headers} tableValues={values} name={"Rooms"} mapping={"/all-rooms"}/>
                </div>
            </Row>
        );
    }
}

export default Rooms;