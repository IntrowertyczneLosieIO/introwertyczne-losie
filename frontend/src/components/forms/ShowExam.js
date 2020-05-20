
import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import NewExamInfo from "./formParts/NewExamInfo";
import Col from "react-bootstrap/Col";
import DataTable from "../dashboard/DataTable";
import Row from "react-bootstrap/Row";

class ShowExam extends React.Component {

    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            validated: false,
            userData: this.getInitialState(),
            majors: [],
            rooms: [],
            examId: 0
        };
    }


    setValidated = (validated) => {
        this.setState({
            validated
        });
    }

    getInitialState = () => {
        if (this.props.initialInputValues) {
            return this.props.initialInputValues;
        }
        else {
            return {
                name: "",
                major: "",
                startDate: "",
                endDate: "",
                recruitmentCycleId: -1,
            }
        }
    }


    getFormData = (target, value) => {
        let currentUserData = this.state.userData;
        this.setState({
            userData: {
                ...currentUserData,
                [target]: value
            }
        })
    }


    hideAndClearState = () => {
        this.setState({
            userData: this.getInitialState()
        });
        this.setValidated(false);
        this.props.handleHide();
    }

    componentDidMount() {
        this.findMajors()
        this.findRooms()
    }

    findRooms() {
        fetch("/newest-rooms")
            .then((response) => {
                return response.json();
            })
            .then((response) => {
                this.setState({
                    rooms: response
                });
            });
    }

    findMajors() {
        fetch("/newest-majors")
            .then((response) => {
                return response.json();
            })
            .then((response) => {
                this.setState({
                    majors: response
                });
                let currentUserData = this.state.userData;
                this.setState({
                    userData: {
                        ...currentUserData
                    }
                })
            });
    }


    render() {
        return (
            <>
                <Modal show={this.props.show} dialogClassName={"custom-width-modal"} onHide={this.hideAndClearState}
                       backdrop={"static"} keyboard={false}>
                    <Modal.Body className={"custom-margins"}>
                        <Form noValidate validated={this.state.validated} ref={this.formRef}>
                            <NewExamInfo options={this.props.options} getFormData={this.getFormData}
                                         majors={this.state.majors}
                                         rooms={this.state.rooms} inputValuesFromState={this.state.userData}/>
                        </Form>
                        <Row>
                            <Col>
                                <DataTable tableHeader={["Subexam id", "Sala", "Data", "Godzina"]}
                                           name={"SubexamsNonEditable"}
                                           mapping={`/newest-subexams/${this.state.userData.id}`}
                                           tableValues={["id", "room", "date", "time"]}/>
                            </Col>
                        </Row>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant={"info"} className={"custom-margins"} onClick={this.hideAndClearState}>
                            Zamknij
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
}

export default ShowExam;
