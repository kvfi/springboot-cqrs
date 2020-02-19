import { ADD_SAMPLE, REMOVE_SAMPLE } from '../samples/types'

const samples = (state = [], action) => {
    switch (action.type) {
        case ADD_SAMPLE: {
            const sample = action.payload
            return [...state, sample]
        }
        case REMOVE_SAMPLE: {
            const sample = action.payload
            return [...state, state.filter(s => s !== sample)]
        }
        default:
            return state
    }
}

export default samples
