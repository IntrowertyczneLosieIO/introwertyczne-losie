import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

class NewExamInfo extends React.Component {

    handleControlChange = (event) => {
        this.props.getFormData(event.target.id, event.target.value);
    }

    render() {
        let majorList = [];
        majorList = this.props.majors.map((major, index) => {
            return <option key={index} value={major}>{major}</option>
        });
        let buildingList = [];
        buildingList = this.props.buildings.map((building, index) => {
            return <option key={index} value={building}>{building}</option>
        });
        let roomList = [];
        roomList = this.props.rooms.map((rooms, index) => {
            return <option key={index} value={rooms}>{rooms}</option>
        });

        return (
            <>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o egzaminie</h5>
                <Form.Row>
                    <FormGroup as={Col} controlId={"name"}>
                        <Form.Label>Egzamin</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"major"}>
                        <Form.Label>Kierunek</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {/*<option hidden disabled value={"default"} />*/}
                            {majorList}
                        </Form.Control>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"startDate"}>
                        <Form.Label>Data rozpoczęcia</Form.Label>
                        <Form.Control type={"date"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"endDate"}>
                        <Form.Label>Data zakończenia</Form.Label>
                        <Form.Control type={"date"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"modeOfStudy"}>
                        <Form.Label>Rodzaj</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {/*<option hidden disabled value={"default"} />*/}
                            <option>stacjonarne</option>
                            <option>niestacjonarne</option>
                        </Form.Control>
                    </FormGroup>
                </Form.Row>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o subegzaminie</h5>
                <Form.Row>
                    <FormGroup as={Col} controlId={"building"}>
                        <Form.Label>Budynek</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {/*<option hidden disabled value={"default"} />*/}
                            {buildingList}
                        </Form.Control>
                    </FormGroup>
                        <FormGroup as={Col} controlId={"room"}>
                            <Form.Label>Sala</Form.Label>
                            <Form.Control as={"select"} onChange={this.handleControlChange} required>
                                {/*<option hidden disabled value={"default"} />*/}
                                {roomList}
                            </Form.Control>
                        </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"subexamDate"}>
                        <Form.Label>Data egzaminu</Form.Label>
                        <Form.Control type={"date"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"subexamTime"}>
                        <Form.Label>Godzina egzaminu</Form.Label>
                        <Form.Control type={"time"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                </Form.Row>

                    <Button variant={"secondary"} className={"custom-margins"} onClick={this.handleSaveAndOpenConfirm}>
                        Dodaj subegzamin
                    </Button>
            </>
        );
    }
}

export default NewExamInfo;