import React from 'react'
import { connect } from 'react-redux'
import { Typography, Button, Form, Input } from 'antd'

import { addSample } from '../../store/samples/actions'
import getSamples from '../../store/samples/selectors'

const { Title } = Typography

const SampleForm = props => {
    const {
        form: { getFieldDecorator, validateFields }
    } = props

    const handleAddSample = e => {
        e.preventDefault()
        validateFields((err, values) => {
            if (!err) {
                props.addSample(values.name)
            }
        })
    }

    return (
        <Form layout="inline" onSubmit={handleAddSample}>
            <Form.Item label="Name">
                {getFieldDecorator('name', {
                    rules: [
                        {
                            required: true
                        }
                    ]
                })(<Input autoFocus />)}
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Add
                </Button>
            </Form.Item>
        </Form>
    )
}

const Home = props => {
    const { samples } = props
    const WrappedSampleForm = Form.create({ name: 'sample_form' })(
        connect(
            state => ({
                samples: getSamples(state)
            }),
            { addSample }
        )(SampleForm)
    )

    return (
        <>
            <Title level={2}>Home</Title>
            <WrappedSampleForm />
            <br />
            Sample size: {samples.length}
            {samples.map(s => {
                return <li key={s}>{s}</li>
            })}
        </>
    )
}

export default connect(
    state => ({
        samples: getSamples(state)
    }),
    { addSample }
)(Home)
