import React from "react";
import DataTable from "./dashboard/DataTable";
import {Row} from "react-bootstrap";
import Sidenav from "./dashboard/Sidenav";

class Majors extends React.Component {
    render() {
        let values = ["id", "fullName", "shortName", "mode", "mixedField", "numberOfPlaces", "faculty", "contactPerson1", "contactPerson2", "annotations"];
        let headers = ["Id", "Pełna nazwa", "Krótka nazwa", "Tryb studiów", "Kierunek łączony", "Liczba miejsc", "Wydział", "Osoba kontaktowa nr 1", "Osoba kontaktowa nr 2", "Uwagi"];

        return (
            <Row className={"no-gutters grid-container"}>
                <div className={"custom-sidenav-bg rounded sidebar"}>
                    <Sidenav objects={this.props.objects}/>
                </div>
                <div className={"border rounded pl-4 pt-4 pr-4 main-content"}>
                    <h2 className={"pb-4 font-weight-light custom-font"}>Kierunki</h2>
                    <DataTable tableHeader={headers} tableValues={values} name={"Majors"} mapping={"/all-majors"}/>
                </div>
            </Row>
        );
    }
}

export default Majors;