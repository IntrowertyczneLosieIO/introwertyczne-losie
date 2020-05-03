import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";

class NewMajorInfo extends React.Component {

    handleControlChange = (event) => {
        this.props.getFormData(event.target.id, event.target.value);
    }

    render() {
        let componentList = [];
        componentList = this.props.faculties.map((faculty, index) => {
            return <option key={index} value={faculty}>{faculty}</option>
        });

        return (
            <>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o kierunku</h5>
                <Form.Row>
                    <FormGroup as={Col} controlId={"major"}>
                        <Form.Label>Kierunek</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"faculty"}>
                        <Form.Label>Wydział</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required defaultValue={"WIEiT"}>
                            {/*<option hidden disabled value={"default"} />*/}
                            {componentList}
                        </Form.Control>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"modeOfStudy"}>
                        <Form.Label>Tryb studiów</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required defaultValue={"stacjonarne"}>
                            {/*<option hidden disabled value={"default"} />*/}
                            <option value={"stacjonarne"}>stacjonarne</option>
                            <option>niestacjonarne</option>
                        </Form.Control>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"type"}>
                        <Form.Label>Typ egzaminu</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required defaultValue={"pisemny"}>
                            {/*<option hidden disabled value={"default"}/>*/}
                            <option value={"pisemny"}>pisemny</option>
                            <option>ustny</option>
                        </Form.Control>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"mixed"}>
                        <Form.Label>Kierunek łączony</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required defaultValue={"Tak"}>
                            {/*<option hidden disabled value={"default"}/>*/}
                            <option value={"Tak"}>Tak</option>
                            <option>Nie</option>
                        </Form.Control>
                    </FormGroup>
                </Form.Row>
            </>
        );
    }
}

export default NewMajorInfo;