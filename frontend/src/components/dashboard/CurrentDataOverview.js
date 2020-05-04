import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import DataTable from "./DataTable";
import Button from "react-bootstrap/Button";
import AddMajor from "../forms/AddMajor";
import AddRoom from "../forms/AddRoom";

class CurrentDataOverview extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showAddNewMajor: false,
            showAddNewRoom: false,

            Majors: {
                displayName: "Kierunki",
                headers: ["Nazwa", "Wydział", "Osoba kontaktowa nr 1", "Osoba kontaktowa nr 2"],
                values: ["fullName", "faculty", "contactPerson1", "contactPerson2"]
            },
            Rooms: {
                displayName: "Sale",
                headers: ["Budynek", "Numer sali", "Rekomendowana pojemność", "Maksymalna pojemność"],
                values: ["localization", "number", "recommendedCapacity", "maximalCapacity"]
            },
            Exams: {
                displayName: "Egzaminy",
                headers: ["Nazwa", "Nazwa", "Nazwa", "Nazwa"],
                values: []
            }
        }
    }

    setShowAddNewMajor = (show) => {
        this.setState({
            showAddNewMajor: show
        });
    }

    setShowAddNewRoom = (show) => {
        this.setState({
            showAddNewRoom: show
        });
    }

    handleShowMajor = () => this.setShowAddNewMajor(true);
    handleHideMajor = () => this.setShowAddNewMajor(false);

    handleShowRoom = () => this.setShowAddNewRoom(true);
    handleHideRoom = () => this.setShowAddNewRoom(false);

    render() {
        const nameComponentMapping = {
            "Majors": AddMajor,
            "Rooms": AddRoom,
            "Exams": AddMajor
        };

        const nameRequestMapping = {
            "Majors": "/newest-majors",
            "Rooms": "/newest-rooms",
            "Exams": "/newest-majors"
        }
        const FormToRender = nameComponentMapping[this.props.name];
        return (
            <div>
                <Row>
                    <Col><h4 className={"mb-3"}>{this.state[this.props.name].displayName}</h4></Col>
                </Row>
                <Row>
                    <Col>
                        <DataTable tableHeader={this.state[this.props.name].headers}
                                   name={this.props.name}
                                   mapping={nameRequestMapping[this.props.name]}
                                   tableValues={this.state[this.props.name].values}/>
                    </Col>
                </Row>
                <Row className={"mb-4"}>
                    <Col xs={2}>
                        <Button variant={"outline-primary"} size={"sm"} block>{this.props.more}</Button>
                    </Col>
                    <Col xs={2}>
                        {/*<Button variant={"success"} className={"mb-3"} size={"sm"} block*/}
                        {/*        onClick={this.handleShowMajor}>{this.props.addNew}</Button>*/}
                        {/*<AddMajor*/}
                        {/*    handleShow={this.handleShowMajor}*/}
                        {/*    handleHide={this.handleHideMajor}*/}
                        {/*    show={this.state.showAddNewMajor}*/}
                        {/*    options={10}/>*/}
                        <Button variant={"success"} className={"mb-3"} size={"sm"} block
                                onClick={this.handleShowRoom}>{this.props.addNew}</Button>
                        <AddRoom
                            handleShow={this.handleShowRoom}
                            handleHide={this.handleHideRoom}
                            show={this.state.showAddNewRoom}
                            options={10}/>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default CurrentDataOverview;