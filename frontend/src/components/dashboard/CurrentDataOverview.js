import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import DataTable from "./DataTable";
import Button from "react-bootstrap/Button";
import AddMajor from "../forms/AddMajor";
import AddExam from "../forms/AddExam";

class CurrentDataOverview extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showAddNewMajor: false,
            showAddNewExam: false,
            Majors: {
                displayName: "Kierunki",
                headers: ["Nazwa", "Wydział", "Osoba kontaktowa nr 1", "Osoba kontaktowa nr 2"],
                values: ["fullName", "faculty", "contactPerson1", "contactPerson2"]
            },
            Rooms: {
                displayName: "Sale",
                headers: ["Nazwa", "Nazwa", "Nazwa", "Nazwa"],
                values: []
            },
            Exams: {
                displayName: "Egzaminy",
                headers: ["Nazwa", "Kierunek", "Forma studiów", "Data rozpoczęcia", "Data zakończenia"],
                values: ["name", "faculty", "modeOfStudy", "startDate", "endDate"]
            }
        }
    }

    setShowAddNewMajor = (show) => {
        this.setState({
            showAddNewMajor: show
        });
    }
    setShowAddNewExam = (show) => {
        this.setState({
            showAddNewExam: show
        });
    }

    handleShowMajor = () => this.setShowAddNewMajor(true);
    handleHideMajor = () => this.setShowAddNewMajor(false);
    handleShowExam = () => this.setShowAddNewExam(true);
    handleHideExam = () => this.setShowAddNewExam(false);

    render() {
        return (
            <div>
                <Row>
                    <Col><h4 className={"mb-3"}>{this.state[this.props.name].displayName}</h4></Col>
                </Row>
                <Row>
                    <Col>
                        <DataTable tableHeader={this.state[this.props.name].headers} name={this.props.name}
                                   tableValues={this.state[this.props.name].values}/>
                    </Col>
                </Row>
                <Row className={"mb-4"}>
                    <Col xs={2}>
                        <Button variant={"outline-primary"} size={"sm"} block>{this.props.more}</Button>
                    </Col>
                    <Col xs={2}>
                        {/*<Button variant={"success"} className={"mb-3"} size={"sm"} block
                                onClick={this.handleShowMajor}>{this.props.addNew}</Button>
                        <AddMajor
                            handleShow={this.handleShowMajor}
                            handleHide={this.handleHideMajor}
                            show={this.state.showAddNewMajor}
                            options={10}/>*/}
                        <Button variant={"success"} className={"mb-3"} size={"sm"} block
                                onClick={this.handleShowExam}>{this.props.addNew}</Button>
                        <AddExam
                            handleShow={this.handleShowExam}
                            handleHide={this.handleHideExam}
                            show={this.state.showAddNewExam}
                            options={10}/>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default CurrentDataOverview;